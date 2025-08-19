package com.github.kmu_shell_we.domain.mission.dto.response;

import com.github.kmu_shell_we.domain.mission.constant.MissionType;
import com.github.kmu_shell_we.domain.mission.entity.Mission;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@Schema(description = "간단한 미션 응답")
@AllArgsConstructor(staticName = "of")
public class SimpleMissionResponse {

    @Schema(description = "미션 ID")
    UUID id;

    @Schema(description = "종류")
    MissionType type;

    @Schema(description = "이름")
    String name;

    public static SimpleMissionResponse from(Mission mission) {

        return SimpleMissionResponse.of(
                mission.getId(),
                mission.getType(),
                mission.getName()
        );
    }
}
