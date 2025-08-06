package com.github.kmu_shell_we.domain.user._schedule.dto.response;

import com.github.kmu_shell_we.domain.user._schedule.entity.Schedule;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "스케줄 목록 응답")
@AllArgsConstructor(staticName = "of")
public class ScheduleListResponse {

    @Schema(description = "스케줄 목록")
    List<ScheduleResponse> schedules;

    public static ScheduleListResponse from(List<Schedule> schedules) {

        return  ScheduleListResponse.of(schedules.stream().map(ScheduleResponse::from).toList());
    }
}
