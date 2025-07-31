package com.github.kmu_shell_we.domain.mission.dto.request;

import com.github.kmu_shell_we.domain.mission.constant.MissionType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "미션 생성 및 수정 요청")
public class UpsertMissionRequest {

    @NotNull
    @Schema(description = "종류")
    MissionType type;

    @NotNull
    @Schema(description = "이름")
    String name;

    @NotNull
    @Schema(description = "보상")
    Integer reward;
}
