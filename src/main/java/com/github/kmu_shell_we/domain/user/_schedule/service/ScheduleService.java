package com.github.kmu_shell_we.domain.user._schedule.service;

import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.github.kmu_shell_we.domain.user._schedule.dto.response.ScheduleResponse;
import com.github.kmu_shell_we.domain.user._schedule.dto.response.TimeRange;
import com.github.kmu_shell_we.domain.user._schedule.entity.Schedule;
import com.github.kmu_shell_we.domain.user._schedule.repository.ScheduleRepository;
import com.github.kmu_shell_we.domain.user.entity.User;
import kong.unirest.core.ContentType;
import kong.unirest.core.Unirest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    private static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/138.0.0.0 Safari/537.36";

    @Transactional(readOnly = true)
    public ScheduleResponse getMySchedule(User user) {

        Map<DayOfWeek, List<TimeRange>> collect = scheduleRepository.findAllByUser(user).stream()
                .collect(Collectors.groupingBy(
                        Schedule::getDayOfWeek,
                        Collectors.mapping(TimeRange::from, Collectors.toList())
                ));

        return ScheduleResponse.of(collect);
    }

    @Transactional
    @SneakyThrows(IOException.class)
    public ScheduleResponse upsertMySchedule(User user, String identifier) {

        scheduleRepository.deleteByUser(user);

        byte[] response = Unirest.post("https://api.everytime.kr/find/timetable/table/friend")
                .contentType(ContentType.APPLICATION_FORM_URLENCODED.getMimeType())
                .header("User-Agent", USER_AGENT)
                .field("identifier", identifier)
                .asBytes()
                .getBody();

        Map<DayOfWeek, List<TimeRange>> collect = new XmlMapper().readTree(response)
                .get("table")
                .get("subject")
                .valueStream()
                .map(node -> node.get("time"))
                .map(node -> node.get("data"))
                .filter(Objects::nonNull)
                .flatMap(node -> node.getNodeType().equals(JsonNodeType.ARRAY) ? node.valueStream() : Stream.of(node))
                .map(node -> Schedule.fromEverytime(node, user))
                .peek(scheduleRepository::save)
                .collect(Collectors.groupingBy(
                        Schedule::getDayOfWeek,
                        Collectors.mapping(TimeRange::from, Collectors.toList())
                ));

        return ScheduleResponse.of(collect);
    }
}
