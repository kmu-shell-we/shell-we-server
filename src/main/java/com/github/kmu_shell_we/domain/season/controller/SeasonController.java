package com.github.kmu_shell_we.domain.season.controller;

import com.github.kmu_shell_we.domain.season._team.dto.response.SimpleTeamResponse;
import com.github.kmu_shell_we.domain.season.dto.response.SeasonResponse;
import com.github.kmu_shell_we.domain.season.entity.Season;
import com.github.kmu_shell_we.domain.season.service.SeasonService;
import com.github.kmu_shell_we.global.response.ApiResponse;
import com.github.kmu_shell_we.global.security.guard.MemberGuard;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@MemberGuard
@RestController
@RequestMapping("/seasons")
@RequiredArgsConstructor
@Tag(name = "시즌")
public class SeasonController {

    private final SeasonService seasonService;

    @GetMapping("/current")
    @Operation(summary = "현재 시즌 조회")
    public ApiResponse<SeasonResponse> getCurrentSeason() {

        return ApiResponse.ok(seasonService.getCurrentSeason());
    }


    @GetMapping("/{season}/ranks")
    @Operation(summary = "현재 시즌 랭크 조회")
    public ApiResponse<List<SimpleTeamResponse>> getSeasonRanks(
            @Parameter(description = "시즌 ID") @PathVariable Season season,
            @Parameter(description = "상위 3개만 조회할지 여부") @RequestParam(required = false, defaultValue = "false") boolean simplified
    ) {

        return ApiResponse.ok(seasonService.getCurrentSeasonRanks(season, simplified));
    }

}
