package com.github.kmu_shell_we.domain.season._team._team_mission.dto.response;

import com.github.kmu_shell_we.domain.mission.dto.response.MissionResponse;
import com.github.kmu_shell_we.domain.season._team._team_mission.entity.TeamMission;
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

    @Schema(description = "팀 미션")
    MissionResponse mission;

    public static TeamMissionResponse from(TeamMission teamMission) {

        return TeamMissionResponse.of(
                teamMission.getId(),
                MissionResponse.from(teamMission.getMission())
        );
    }
}
