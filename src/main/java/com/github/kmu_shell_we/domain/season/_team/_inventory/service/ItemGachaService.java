package com.github.kmu_shell_we.domain.season._team._inventory.service;

import com.github.kmu_shell_we.domain.season._team._inventory.constant.ItemType;
import com.github.kmu_shell_we.domain.season._team._inventory.dto.response.item.Item;
import com.github.kmu_shell_we.domain.season._team._inventory.entity.Inventory;
import com.github.kmu_shell_we.domain.season._team._inventory.repository.InventoryRepository;
import com.github.kmu_shell_we.domain.season._team.entity.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemGachaService {

    private final InventoryRepository inventoryRepository;

    public ItemType drawRandomItem() {
        ItemType[] allItems = ItemType.values();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return allItems[random.nextInt(allItems.length)];
    }

    public Item drawAndAddToInventory(Team team) {

        ItemType drawnItem = drawRandomItem();

        Inventory inventory = inventoryRepository.findByTeam(team)
                .orElseGet(() -> {
                    Inventory newInventory = Inventory.builder()
                            .team(team)
                            .items(new ArrayList<>())
                            .build();
                    return inventoryRepository.save(newInventory);
                });

        inventory.getItems().add(drawnItem);
        inventoryRepository.save(inventory);

        return createItemResponse(drawnItem);
    }

    private Item createItemResponse(ItemType itemType) {

        return switch (itemType) {
            case NEXT_MISSION_EXP_2X -> new Item.NextMissionExp2x();
            case DECREASE_OTHER_TEAM_EXP -> new Item.DecreaseOtherTeamExp();
            case MISSION_RESET -> new Item.MissionReset();
        };
    }
}
