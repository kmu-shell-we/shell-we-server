package com.github.kmu_shell_we.domain.team.dto.response;

import com.github.kmu_shell_we.domain.team.entity.Team;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor(staticName = "of")
@Schema(description = "팀 응답")
public class TeamResponse {

    @Schema(description = "팀 ID")
    UUID id;

    @Schema(description = "이름")
    String name;

    @Schema(description = "경험치")
    Integer experience;

    @Schema(description = "포인트")
    Integer point;

    public static TeamResponse from(Team team) {

        return TeamResponse.of(team.getId(), team.getName(), team.getExperience(), team.getPoint());
    }
}
