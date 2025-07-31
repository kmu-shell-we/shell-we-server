package com.github.kmu_shell_we.domain.season._team.dto.response;

import com.github.kmu_shell_we.domain.season._team.entity.Team;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor(staticName = "of")
@Schema(description = "간략화된 팀 응답")
public class SimpleTeamResponse {

    @Schema(description = "팀 ID")
    UUID id;

    @Schema(description = "이름")
    String name;

    public static SimpleTeamResponse from(Team team) {

        return SimpleTeamResponse.of(team.getId(), team.getName());
    }
}