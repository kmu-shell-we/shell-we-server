package com.github.kmu_shell_we.domain.season._team._item.dto.response;


import com.github.kmu_shell_we.domain.season._team._item.constant.ItemType;
import com.github.kmu_shell_we.domain.season._team._item.entity.Item;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Optional;

@Data
@AllArgsConstructor(staticName = "of")
@Schema(description = "아이템 응답")
public class ItemResponse {

    @Schema(description = "아이템 타입")
    private final String type;

    @Schema(description = "아이템 이름")
    private final String name;

    @Schema(description = "아이템 설명")
    private final String description;

    @Schema(description = "아이템 감소량", nullable = true)
    private final Integer decreaseAmount;

    public ItemResponse(ItemType itemType) {

        this.type = itemType.name();
        this.name = itemType.getName();
        this.description = itemType.getDescription();
        this.decreaseAmount = itemType.getDecreaseAmount();
    }

    public static ItemResponse from(Item item) {

        return Optional.ofNullable(item)
                .map(i -> new ItemResponse(i.getType()))
                .orElse(null);
    }
}