package com.github.kmu_shell_we.domain.season._team._mission_team.dto.response;

import com.github.kmu_shell_we.domain.season._team._mission_team.entity.TeamMission;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor(staticName = "of")
@Schema(description = "팀 미션 목록 응답")
public class TeamMissionListResponse {

    @Schema(description = "팀 미션 목록")
    List<TeamMissionResponse> teamMissions;

    public static TeamMissionListResponse from(List<TeamMission> teamMissions) {

        return TeamMissionListResponse.of(teamMissions.stream().map(TeamMissionResponse::from).toList());
    }
}
