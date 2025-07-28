package com.github.kmu_shell_we.domain.team.dto.response;

import com.github.kmu_shell_we.domain.team.entity.Team;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor(staticName = "of")
@Schema(description = "팀 응답 DTO")
public class SimpleTeamResponse {

    @Schema(description = "팀 ID")
    UUID id;

    @Schema(description = "팀 이름")
    String name;

    public static SimpleTeamResponse from(Team team) {

        return SimpleTeamResponse.of(
                team.getId(),
                team.getName()
        );
    }
}
