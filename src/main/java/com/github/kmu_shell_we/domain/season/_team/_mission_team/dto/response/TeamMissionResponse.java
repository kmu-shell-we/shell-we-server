package com.github.kmu_shell_we.domain.season._team._mission_team.dto.response;

import com.github.kmu_shell_we.domain.mission.constant.MissionType;
import com.github.kmu_shell_we.domain.mission.dto.response.MissionResponse;
import com.github.kmu_shell_we.domain.mission.entity.Mission;
import com.github.kmu_shell_we.domain.season._team._mission_team.entity.TeamMission;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor(staticName = "of")
@Schema(description = "팀 미션 응답")
public class TeamMissionResponse {

    @Schema(description = "팀 미션 ID")
    UUID id;

    @Schema(description = "팀 미션 타입")
    MissionType type;

    @Schema(description = "팀 미션")
    MissionResponse missionResponse;

    public static TeamMissionResponse from(TeamMission teamMission) {

        Mission mission = teamMission.getMission();

        return TeamMissionResponse.of(
                teamMission.getId(),
                mission.getType(),
                MissionResponse.from(mission)
        );
    }
}
