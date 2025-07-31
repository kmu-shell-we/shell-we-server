package com.github.kmu_shell_we.domain.team.dto.response;

import com.github.kmu_shell_we.domain.team.entity.Team;
import com.github.kmu_shell_we.domain.user.dto.response.SimpleUserResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor(staticName = "of")
@Schema(description = "다른 팀 정보 응답")
public class GetOtherTeamResponse {

    @Schema(description = "팀")
    OtherTeamResponse team;

    @Schema(description = "멤버")
    List<SimpleUserResponse> users;

    // TODO: 팀 멤버 연결 후, 실제 멤버 받아오기
    public static GetOtherTeamResponse from(Team team) {

        return GetOtherTeamResponse.of(OtherTeamResponse.from(team), List.of());
    }
}
