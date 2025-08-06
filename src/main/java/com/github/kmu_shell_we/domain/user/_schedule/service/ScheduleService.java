package com.github.kmu_shell_we.domain.user._schedule.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.github.kmu_shell_we.domain.user._schedule.dto.request.UpsertScheduleRequest;
import com.github.kmu_shell_we.domain.user._schedule.dto.response.ScheduleListResponse;
import com.github.kmu_shell_we.domain.user._schedule.entity.Schedule;
import com.github.kmu_shell_we.domain.user._schedule.exception.ScheduleExceptions;
import com.github.kmu_shell_we.domain.user._schedule.repository.ScheduleRepository;
import com.github.kmu_shell_we.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleListResponse upsertMySchedule(UpsertScheduleRequest request, User user) {

        // 1. identifier 추출
        String identifier = extractIdentifier(request.getUrl());

        try {

            // 2. xml 응답 받기
            String xmlString = getResponse(identifier);

            // 3. 파싱해서 시간 정보 가져오기(요일, 시작 시간, 종료 시간)
            ArrayList<ArrayList<Object>> times = xmlParser(xmlString);

            // 4. 각 시간 정보에 관한 Schedule 엔티티 생성 후 레포지토리에 저장 후 리스트 형태로 반환
            List<Schedule> schedules = new ArrayList<>();

            for (ArrayList<Object> time : times) {
                Schedule schedule = new Schedule((Schedule.DayOfWeek) time.get(0), (String) time.get(1), (String) time.get(2), user);
                schedules.add(schedule);
                scheduleRepository.save(schedule);
            }

            return ScheduleListResponse.from(schedules);

        } catch (IOException e) {
            throw ScheduleExceptions.NOT_FOUND_SCHEDULE.toException();
        } catch (Exception e) {
            throw ScheduleExceptions.SCHEDULE_PARSE_FAILED.toException();
        }
    }

    private String extractIdentifier(String url) {

        return url.split("@")[1];
    }

    private String getResponse(String identifier) throws IOException {

        // 해당 URL과의 connection 생성
        URL url = new URL("https://api.everytime.kr/find/timetable/table/friend");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        // 헤더 정보
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/138.0.0.0 Safari/537.36");
        conn.setDoOutput(true);

        // 바디 정보
        String body = "identifier=" + URLEncoder.encode(identifier, StandardCharsets.UTF_8) +
                "&friendInfo=" + URLEncoder.encode("true", StandardCharsets.UTF_8);

        // 해당 URL에 헤더와 바디 정보 넘기기
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
        bw.write(body);
        bw.flush();
        bw.close();

        // 서버로부터 데이터 읽어오기
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        StringBuilder sb = new StringBuilder();
        String line = null;

        while ((line = br.readLine()) != null) {
            sb.append(line);
        }

        // XML 형태의 String 반환
        return sb.toString();
    }

    private ArrayList<ArrayList<Object>> xmlParser(String xmlString) throws Exception {

        XmlMapper xmlMapper = new XmlMapper();
        JsonNode root = xmlMapper.readTree(xmlString);

        JsonNode subjects = root.path("table").path("subject");

        ArrayList<ArrayList<Object>> schedules = new ArrayList<>();

        if (subjects.isArray()) {
            for (JsonNode subject : subjects) {
                JsonNode times = subject.path("time").path("data");
                if (times.isArray()) {
                    for (JsonNode time : times) {
                        ArrayList<Object> dates = new ArrayList<>();
                        dates.add(intToDay(time.path("day").asText()));
                        dates.add(time.path("starttime").asText());
                        dates.add(time.path("endtime").asText());

                        schedules.add(dates);
                    }
                }
            }
        }

        System.out.println(schedules);

        return schedules;
    }

    private Schedule.DayOfWeek intToDay(String day) {

        return switch (day) {
            case "0" -> Schedule.DayOfWeek.MONDAY;
            case "1" -> Schedule.DayOfWeek.TUESDAY;
            case "2" -> Schedule.DayOfWeek.WEDNESDAY;
            case "3" -> Schedule.DayOfWeek.THURSDAY;
            case "4" -> Schedule.DayOfWeek.FRIDAY;
            case "5" -> Schedule.DayOfWeek.SATURDAY;
            case "6" -> Schedule.DayOfWeek.SUNDAY;
            default -> null;
        };
    }

    @Transactional(readOnly = true)
    public ScheduleListResponse getMySchedule(User user) {

        List<Schedule> schedules = scheduleRepository.findByUser(user);

        return ScheduleListResponse.from(schedules);
    }
}
