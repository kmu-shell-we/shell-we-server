package com.github.kmu_shell_we.domain.season._team._mission_team.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "특별 팀 미션 생성 요청")
public class CreateSpecialTeamMissionRequest {

    @NotNull
    @Schema(description = "시작 일시")
    LocalDateTime startedAt;

    @NotNull
    @Schema(description = "종료 일시")
    LocalDateTime endedAt;
}
