package com.github.kmu_shell_we.domain.user._schedule.dto.response;

import com.github.kmu_shell_we.domain.user._schedule.entity.Schedule;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@Data
@Schema(description = "시간 범위")
@AllArgsConstructor(staticName = "of")
public class TimeRange {

    LocalTime startedAt;
    LocalTime endedAt;

    public static TimeRange from(Schedule schedule) {

        return TimeRange.of(schedule.getStartedAt(), schedule.getEndedAt());
    }
}
