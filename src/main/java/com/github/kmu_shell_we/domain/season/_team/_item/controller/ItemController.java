package com.github.kmu_shell_we.domain.season._team._item.controller;

import com.github.kmu_shell_we.domain.season._team._item.dto.response.ItemListResponse;
import com.github.kmu_shell_we.domain.season._team._item.dto.response.ItemResponse;
import com.github.kmu_shell_we.domain.season._team._item.service.ItemService;
import com.github.kmu_shell_we.domain.season._team._item.service.ItemGachaService;
import com.github.kmu_shell_we.domain.season._team.entity.Team;
import com.github.kmu_shell_we.domain.season.entity.Season;
import com.github.kmu_shell_we.global.response.ApiResponse;
import com.github.kmu_shell_we.global.security.guard.MemberGuard;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@MemberGuard
@RestController
@RequestMapping("/seasons/{season}/teams/{team}/items")
@RequiredArgsConstructor
@Tag(name = "팀 아이템")
public class ItemController {

    private final ItemService itemService;
    private final ItemGachaService itemGachaService;

    @GetMapping
    @Operation(summary = "특정 팀 인벤토리 조회")
    public ApiResponse<ItemListResponse> getItems(
            @Parameter(description = "시즌 ID") @PathVariable Season season, @Parameter(description = "팀 ID") @PathVariable Team team
    ) {
        return ApiResponse.ok(itemService.getItems(season, team));
    }

    @PostMapping("/draw")
    @Operation(summary = "아이템 뽑기")
    public ApiResponse<ItemResponse> drawItem(
            @Parameter(description = "시즌 ID") @PathVariable Season season, @Parameter(description = "팀 ID") @PathVariable Team team
    ) {
        return ApiResponse.ok(itemGachaService.drawItem(team));
    }
}
