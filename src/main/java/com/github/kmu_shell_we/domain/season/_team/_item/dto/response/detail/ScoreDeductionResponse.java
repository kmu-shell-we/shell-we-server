package com.github.kmu_shell_we.domain.season._team._item.dto.response.detail;

import com.github.kmu_shell_we.domain.season._team._item.dto.response.ItemResponse;
import com.github.kmu_shell_we.domain.season._team.entity.Team;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Schema(description = "[아이템] 다른 팀 점수 깎기 응답 DTO")
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor(staticName = "of")
public class ScoreDeductionResponse extends ItemResponse {

    @Schema(description = "다른 팀")
    Team team;

    @Schema(description = "깎은 점수")
    Integer amount;
}
