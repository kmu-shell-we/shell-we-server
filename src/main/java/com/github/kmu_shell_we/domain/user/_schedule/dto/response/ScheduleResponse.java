package com.github.kmu_shell_we.domain.user._schedule.dto.response;

import com.github.kmu_shell_we.domain.user._schedule.entity.Schedule;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor(staticName = "of")
@Schema(description = "스케줄 응답 DTO")
public class ScheduleResponse {

    Map<DayOfWeek, List<TimeRange>> schedules;

    public static ScheduleResponse from(List<Schedule> schedules) {

        Map<DayOfWeek, List<TimeRange>> collect = schedules.stream()
        .collect(Collectors.groupingBy(
                Schedule::getDayOfWeek,
                Collectors.mapping(TimeRange::from, Collectors.toList())
        ));

        return ScheduleResponse.of(collect);
    }
}
