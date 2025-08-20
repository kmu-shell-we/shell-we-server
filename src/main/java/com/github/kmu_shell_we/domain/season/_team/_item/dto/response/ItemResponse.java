package com.github.kmu_shell_we.domain.season._team._item.dto.response;

import com.github.kmu_shell_we.domain.season._team._item.constant.ItemType;
import com.github.kmu_shell_we.domain.season._team._item.entity.Item;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema(name = "아이템 응답 DTO")
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class ItemResponse {

    @Schema(description = "뽑은 아이템")
    ItemType type;

    public static ItemResponse from(Item item) {

        return ItemResponse.of(item.getType());
    }
}
