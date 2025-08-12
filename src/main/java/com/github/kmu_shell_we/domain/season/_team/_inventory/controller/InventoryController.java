package com.github.kmu_shell_we.domain.season._team._inventory.controller;

import com.github.kmu_shell_we.domain.season._team._inventory.dto.response.InventoryResponse;
import com.github.kmu_shell_we.domain.season._team._inventory.dto.response.item.Item;
import com.github.kmu_shell_we.domain.season._team._inventory.service.InventoryService;
import com.github.kmu_shell_we.domain.season._team._inventory.service.ItemGachaService;
import com.github.kmu_shell_we.domain.season._team.entity.Team;
import com.github.kmu_shell_we.domain.season.entity.Season;
import com.github.kmu_shell_we.global.response.ApiResponse;
import com.github.kmu_shell_we.global.security.guard.MemberGuard;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@MemberGuard
@RestController
@RequestMapping("/seasons/{season}/teams/{team}/items")
@RequiredArgsConstructor
@Tag(name = "인벤토리")
public class InventoryController {

    private final InventoryService inventoryService;
    private final ItemGachaService itemGachaService;

    @GetMapping
    public ApiResponse<InventoryResponse> getInventory(
            @Parameter(description = "시즌 ID") @PathVariable Season season,
            @Parameter(description = "팀 ID") @PathVariable Team team
    ) {

        InventoryResponse response = inventoryService.getInventory(season, team);
        return ApiResponse.ok(response);
    }

    @PostMapping("/draw")
    public ApiResponse<Item> drawItem(
            @Parameter(description = "팀 ID") @PathVariable Team team
    ) {

        Item drawnItem = itemGachaService.drawAndAddToInventory(team);
        return ApiResponse.ok(drawnItem);
    }
}
