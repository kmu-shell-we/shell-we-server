package com.github.kmu_shell_we.domain.season._team._item.dto.response.detail;

import com.github.kmu_shell_we.domain.season._team._item.dto.response.ItemResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Schema(description = "[아이템] 꽝 응답 DTO")
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor(staticName = "of")
public class BoomResponse extends ItemResponse {
}
