package com.github.kmu_shell_we.domain.season._team._item.dto.response.detail;

import com.github.kmu_shell_we.domain.season._team._item.constant.ItemType;
import com.github.kmu_shell_we.domain.season._team._item.dto.response.ItemResponse;
import com.github.kmu_shell_we.domain.season._team.dto.response.SimpleTeamResponse;
import com.github.kmu_shell_we.domain.season._team.entity.Team;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Schema(description = "[아이템] 다른 팀 점수 깎기 응답 DTO")
@EqualsAndHashCode(callSuper = true)
public class ScoreDeductionResponse extends ItemResponse {

    @Schema(description = "다른 팀")
    SimpleTeamResponse team;

    @Schema(description = "깎은 점수")
    Integer amount;

    public static ScoreDeductionResponse of(SimpleTeamResponse team, Integer amount) {

        ScoreDeductionResponse response = new ScoreDeductionResponse();

        response.setType(ItemType.SCORE_DEDUCTION);
        response.setTeam(team);
        response.setAmount(amount);

        return response;
    }

    public static ScoreDeductionResponse from(Team team, Integer amount) {

        return of(SimpleTeamResponse.from(team), amount);
    }
}
