package com.github.kmu_shell_we.domain.season._team._inventory.service;

import com.github.kmu_shell_we.domain.season._team._inventory.dto.response.InventoryResponse;
import com.github.kmu_shell_we.domain.season._team._inventory.entity.Inventory;
import com.github.kmu_shell_we.domain.season._team.entity.Team;
import com.github.kmu_shell_we.domain.season.entity.Season;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    @Transactional(readOnly = true)
    @PreAuthorize("#ignoredSeason.isCurrentSeason() and #ignoredSeason == #team.season")
    public InventoryResponse getInventory(Season ignoredSeason, Team team) {

        Inventory inventory = team.getInventory();

        if (inventory == null) {
            return InventoryResponse.from(null);
        }

        return InventoryResponse.from(inventory);
    }
}
