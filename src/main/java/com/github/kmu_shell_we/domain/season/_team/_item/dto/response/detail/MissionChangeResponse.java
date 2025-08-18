package com.github.kmu_shell_we.domain.season._team._item.dto.response.detail;

import com.github.kmu_shell_we.domain.mission.entity.Mission;
import com.github.kmu_shell_we.domain.season._team._item.dto.response.ItemResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Schema(description = "[아이템] 미션 변경 응답 DTO")
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor(staticName = "of")
public class MissionChangeResponse extends ItemResponse {

    @Schema(description = "이전 미션")
    Mission previous;

    @Schema(description = "이후 미션")
    Mission after;
}
