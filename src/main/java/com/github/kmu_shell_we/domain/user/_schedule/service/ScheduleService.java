package com.github.kmu_shell_we.domain.user._schedule.service;

import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.github.kmu_shell_we.domain.user._schedule.dto.response.ScheduleResponse;
import com.github.kmu_shell_we.domain.user._schedule.entity.Schedule;
import com.github.kmu_shell_we.domain.user._schedule.repository.ScheduleRepository;
import com.github.kmu_shell_we.domain.user.entity.User;
import com.github.kmu_shell_we.global.config.CustomConfig;
import kong.unirest.core.ContentType;
import kong.unirest.core.Unirest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final static XmlMapper xmlMapper = new XmlMapper();
    private final ScheduleRepository scheduleRepository;
    private final CustomConfig.UserAgentHolder userAgentHolder;

    @Transactional(readOnly = true)
    public ScheduleResponse getMySchedule(User user) {

        List<Schedule> schedules = scheduleRepository.findAllByUser(user);

        return ScheduleResponse.from(schedules);
    }

    @Transactional
    @SneakyThrows(IOException.class)
    public ScheduleResponse upsertMySchedule(User user, String identifier) {

        scheduleRepository.deleteByUser(user);

        byte[] response = Unirest.post("https://api.everytime.kr/find/timetable/table/friend")
                .contentType(ContentType.APPLICATION_FORM_URLENCODED.getMimeType())
                .header("User-Agent", userAgentHolder.getUserAgent())
                .field("identifier", identifier)
                .asBytes()
                .getBody();

        List<Schedule> schedules = xmlMapper.readTree(response)
                .get("table")
                .get("subject")
                .valueStream()
                .map(node -> node.get("time"))
                .map(node -> node.get("data"))
                .filter(Objects::nonNull)
                .flatMap(node -> node.getNodeType().equals(JsonNodeType.ARRAY) ? node.valueStream() : Stream.of(node))
                .map(node -> Schedule.fromEverytime(node, user))
                .peek(scheduleRepository::save)
                .toList();

        return ScheduleResponse.from(schedules);
    }
}
