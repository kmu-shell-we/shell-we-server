package com.github.kmu_shell_we.domain.mission.dto.response;

import com.github.kmu_shell_we.domain.mission._season_mission.entity.SeasonMission;
import com.github.kmu_shell_we.domain.mission.constant.MissionType;
import com.github.kmu_shell_we.domain.mission.entity.Mission;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor(staticName = "of")
@Schema(description = "미션 응답")
public class MissionResponse {

    @Schema(description = "미션 ID")
    UUID id;

    @Schema(description = "종류")
    MissionType type;

    @Schema(description = "이름")
    String name;

    @Schema(description = "보상")
    Integer reward;

    public static MissionResponse from(Mission mission) {

        return MissionResponse.of(mission.getId(), mission.getType(), mission.getName(), mission.getReward());
    }

    public static MissionResponse from(SeasonMission seasonMission) {

        return from(seasonMission.getMission());
    }
}
