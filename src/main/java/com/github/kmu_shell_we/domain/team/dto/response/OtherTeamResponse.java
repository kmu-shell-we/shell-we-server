package com.github.kmu_shell_we.domain.team.dto.response;

import com.github.kmu_shell_we.domain.team.entity.Team;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor(staticName = "of")
@Schema(description = "다른 팀 응답")
public class OtherTeamResponse {

    @Schema(description = "팀 ID")
    UUID id;

    @Schema(description = "팀 이름")
    String name;

    @Schema(description = "팀 경험치")
    Integer experience;

    public static OtherTeamResponse from(Team team) {

        return OtherTeamResponse.of(team.getId(), team.getName(), team.getExperience());
    }
}
