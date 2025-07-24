package com.github.kmu_shell_we.domain.season.controller;

import com.github.kmu_shell_we.domain.season.dto.request.CreateSeasonRequest;
import com.github.kmu_shell_we.domain.season.dto.request.UpdateSeasonRequest;
import com.github.kmu_shell_we.domain.season.dto.response.SeasonListResponse;
import com.github.kmu_shell_we.domain.season.dto.response.SeasonResponse;
import com.github.kmu_shell_we.domain.season.service.SeasonService;
import com.github.kmu_shell_we.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

//@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping("/admin/seasons")
@RequiredArgsConstructor
@Tag(name = "관지라용 시즌 API", description = "시즌 생성, 수정, 삭제를 위한 API")
public class AdminSeasonController {

    private final SeasonService seasonService;

    // 시즌 전체 조회
    @GetMapping
    @Operation(summary = "시즌 전체 조회", description = "시즌 목록을 조회합니다.")
    public ApiResponse<SeasonListResponse> getSeasons() {

        return ApiResponse.ok(seasonService.getSeasons());
    }

    // 시즌 상세 조회
    @GetMapping("/{seasonId}")
    @Operation(summary = "시즌 상세 조회", description = "특정 시즌의 상세 정보를 조회합니다.")
    public ApiResponse<SeasonResponse> getSeason(
            @Parameter(description = "시즌 ID", example = "123e4567-e89b-12d3-a456-426614174000")
            @PathVariable UUID seasonId) {

        return ApiResponse.ok(seasonService.getSeason(seasonId));
    }

    // 시즌 생성
    @PostMapping
    @Operation(summary = "시즌 생성", description = "새로운 시즌을 생성합니다.")
    public ApiResponse<SeasonResponse> createSeason(@RequestBody @Valid CreateSeasonRequest request) {

        return ApiResponse.ok(seasonService.createSeason(request));
    }

    // 시즌 수정
    @PutMapping("/{seasonId}")
    @Operation(summary = "시즌 수정", description = "특정 시즌의 정보를 수정합니다.")
    public ApiResponse<SeasonResponse> updateSeason(
            @Parameter(description = "시즌 ID", example = "123e4567-e89b-12d3-a456-426614174000")
            @PathVariable UUID seasonId,
            @RequestBody @Valid UpdateSeasonRequest request) {

        return ApiResponse.ok(seasonService.updateSeason(seasonId, request));
    }

    // 시즌 삭제
    @DeleteMapping("/{seasonId}")
    @Operation(summary = "시즌 삭제", description = "특정 시즌을 삭제합니다.")
    public ApiResponse<SeasonResponse> deleteSeason(
            @Parameter(description = "시즌 ID", example = "123e4567-e89b-12d3-a456-426614174000")
            @PathVariable UUID seasonId) {

        return ApiResponse.ok(seasonService.deleteSeason(seasonId));
    }
}
