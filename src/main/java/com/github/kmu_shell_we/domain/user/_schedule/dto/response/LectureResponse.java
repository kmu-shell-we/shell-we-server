package com.github.kmu_shell_we.domain.user._schedule.dto.response;

import com.github.kmu_shell_we.domain.user._schedule.entity.Schedule;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Schema(description = "과목 시간 응답")
@AllArgsConstructor(staticName = "of")
public class LectureResponse {

    @Schema(description = "시작 시간")
    String startedAt;

    @Schema(description = "종료 시간")
    String endedAt;

    public static LectureResponse from(Schedule schedule) {

        return LectureResponse.of(
                schedule.getStartedAt(),
                schedule.getEndedAt()
        );
    }
}
