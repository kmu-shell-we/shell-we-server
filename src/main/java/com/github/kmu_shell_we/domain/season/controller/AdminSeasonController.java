package com.github.kmu_shell_we.domain.season.controller;

import com.github.kmu_shell_we.domain.season.dto.request.CreateSeasonRequest;
import com.github.kmu_shell_we.domain.season.dto.request.UpdateSeasonRequest;
import com.github.kmu_shell_we.domain.season.dto.response.SeasonListResponse;
import com.github.kmu_shell_we.domain.season.dto.response.SeasonResponse;
import com.github.kmu_shell_we.domain.season.service.AdminSeasonService;
import com.github.kmu_shell_we.global.response.ApiResponse;
import com.github.kmu_shell_we.global.security.guard.AdminGuard;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AdminGuard
@RestController
@RequestMapping("/admin/seasons")
@RequiredArgsConstructor
@Tag(name = "[관리자] 시즌")
public class AdminSeasonController {

    private final AdminSeasonService seasonService;

    @GetMapping
    @Operation(summary = "전체 시즌 목록 조회")
    public ApiResponse<SeasonListResponse> getSeasons() {

        return ApiResponse.ok(seasonService.getSeasons());
    }

    @GetMapping("/{seasonId}")
    @Operation(summary = "시즌 상세 조회")
    public ApiResponse<SeasonResponse> getSeason(@Parameter(description = "시즌 ID") @PathVariable UUID seasonId) {

        return ApiResponse.ok(seasonService.getSeason(seasonId));
    }

    @PostMapping
    @Operation(summary = "시즌 생성")
    public ApiResponse<SeasonResponse> createSeason(@RequestBody @Valid CreateSeasonRequest request) {

        return ApiResponse.ok(seasonService.createSeason(request));
    }

    @PutMapping("/{seasonId}")
    @Operation(summary = "시즌 수정")
    public ApiResponse<SeasonResponse> updateSeason(
            @Parameter(description = "시즌 ID") @PathVariable UUID seasonId,
            @RequestBody @Valid UpdateSeasonRequest request
    ) {

        return ApiResponse.ok(seasonService.updateSeason(seasonId, request));
    }

    @DeleteMapping("/{seasonId}")
    @Operation(summary = "시즌 삭제")
    public ApiResponse<Void> deleteSeason(@Parameter(description = "시즌 ID") @PathVariable UUID seasonId) {

        seasonService.deleteSeason(seasonId);

        return ApiResponse.ok();
    }
}
