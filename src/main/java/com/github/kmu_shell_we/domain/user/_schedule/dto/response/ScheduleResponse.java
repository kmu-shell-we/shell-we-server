package com.github.kmu_shell_we.domain.user._schedule.dto.response;

import com.github.kmu_shell_we.domain.user._schedule.entity.Schedule;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Schema(description = "스케줄 응답")
@AllArgsConstructor(staticName = "of")
public class ScheduleResponse {

    @Schema(description = "요일")
    Schedule.DayOfWeek dayOfWeek;

    @Schema(description = "시작 시간")
    String startedAt;

    @Schema(description = "종료 시간")
    String endedAt;

    public static ScheduleResponse from(Schedule schedule) {

        return ScheduleResponse.of(
                schedule.getDayOfWeek(),
                schedule.getStartedAt(),
                schedule.getEndedAt()
        );
    }
}
