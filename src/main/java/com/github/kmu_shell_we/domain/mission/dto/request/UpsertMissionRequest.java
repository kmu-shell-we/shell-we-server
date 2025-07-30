package com.github.kmu_shell_we.domain.mission.dto.request;

import com.github.kmu_shell_we.domain.mission.dto.response.MissionType;
import com.github.kmu_shell_we.domain.mission.entity.Mission;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "미션 생성 및 수정 요청 DTO")
public class UpsertMissionRequest {

    @NotNull
    @Schema(description = "미션 이름", example = "릴스 찍기")
    String name;

    @NotNull
    @Schema(description = "미션 보상", example = "150")
    Integer reward;

    @NotNull
    @Schema(description = "미션 타입", example = "WEEKLY")
    MissionType type;
}
