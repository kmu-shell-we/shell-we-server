package com.github.kmu_shell_we.domain.user._schedule.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor(staticName = "of")
@Schema(description = "스케줄 응답 DTO")
public class ScheduleResponse {

    Map<DayOfWeek, List<TimeRange>> schedules;
}
