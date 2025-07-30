package com.github.kmu_shell_we.domain.mission.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
@Schema(description = "시즌 미션 생성 요청 DTO")
public class UpsertSeasonMissionRequest {

    @NotNull
    @Schema(description = "시즌 ID", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID seasonId;

    @NotNull
    @Schema(description = "미션 ID", example = "123e4567-e89b-12d3-a456-426614174001")
    private UUID missionId;

}
