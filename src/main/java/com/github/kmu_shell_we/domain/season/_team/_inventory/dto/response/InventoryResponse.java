package com.github.kmu_shell_we.domain.season._team._inventory.dto.response;

import com.github.kmu_shell_we.domain.season._team._inventory.dto.response.item.Item;
import com.github.kmu_shell_we.domain.season._team._inventory.entity.Inventory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor(staticName = "of")
@Schema(description = "인벤토리 응답")
public class InventoryResponse {

    @Schema(description = "아이템 목록")
    private List<Item> items;

    public static InventoryResponse from(Inventory inventory) {
        if (inventory == null || inventory.getItems() == null) {
            return InventoryResponse.of(List.of());
        }

        List<Item> itemResponses = inventory.getItems().stream()
                .map(itemType -> switch (itemType) {
                    case NEXT_MISSION_EXP_2X -> new Item.NextMissionExp2x();
                    case DECREASE_OTHER_TEAM_EXP -> new Item.DecreaseOtherTeamExp();
                    case MISSION_RESET       -> new Item.MissionReset();
                })
                .collect(Collectors.toList());

        return InventoryResponse.of(itemResponses);
    }
}