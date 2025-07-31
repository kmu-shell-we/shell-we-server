package com.github.kmu_shell_we.domain.season._team.dto.response;

import com.github.kmu_shell_we.domain.season._team._user_team.entity.UserTeam;
import com.github.kmu_shell_we.domain.season._team.entity.Team;
import com.github.kmu_shell_we.domain.user.dto.response.SimpleUserResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor(staticName = "of")
@Schema(description = "팀 정보 응답")
public class GetTeamResponse {

    @Schema(description = "팀")
    TeamResponse team;

    @Schema(description = "멤버")
    List<SimpleUserResponse> users;

    public static GetTeamResponse from(Team team) {

        return GetTeamResponse.of(
                TeamResponse.from(team),
                team.getUserTeams().stream()
                        .map(UserTeam::getUser)
                        .map(SimpleUserResponse::from)
                        .toList()
        );
    }
}