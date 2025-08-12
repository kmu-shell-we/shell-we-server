package com.github.kmu_shell_we.domain.season._team._inventory.controller;

import com.github.kmu_shell_we.domain.season._team._inventory.dto.response.InventoryResponse;
import com.github.kmu_shell_we.domain.season._team._inventory.service.AdminInventoryService;
import com.github.kmu_shell_we.domain.season._team.entity.Team;
import com.github.kmu_shell_we.domain.season.entity.Season;
import com.github.kmu_shell_we.global.response.ApiResponse;
import com.github.kmu_shell_we.global.security.guard.AdminGuard;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AdminGuard
@RestController
@RequestMapping("/admin/seasons/{season}/teams/{team}/items")
@RequiredArgsConstructor
@Tag(name = "[관리자] 인벤토리")
public class AdminInventoryController {

    private final AdminInventoryService adminInventoryService;

    @GetMapping("")
    @Operation(summary = "특정 팀 인벤토리 조회")
    public ApiResponse<InventoryResponse> getInventory(
            @Parameter(description = "시즌 ID") @PathVariable Season season,
            @Parameter(description = "팀 ID") @PathVariable Team team
    ) {

        InventoryResponse response = adminInventoryService.getInventory(season, team);
        return ApiResponse.ok(response);
    }
}