package com.github.kmu_shell_we.domain.season._team.dto.response;

import com.github.kmu_shell_we.domain.season._team.entity.Team;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor(staticName = "of")
@Schema(description = "팀 목록 응답")
public class TeamListResponse {

    @Schema(description = "팀 목록")
    List<SimpleTeamResponse> teams;

    public static TeamListResponse from(List<Team> teams) {

        return TeamListResponse.of(teams.stream().map(SimpleTeamResponse::from).toList());
    }
}