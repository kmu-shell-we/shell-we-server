package com.github.kmu_shell_we.domain.season.controller;

import com.github.kmu_shell_we.domain.season.dto.request.CreateSeasonRequest;
import com.github.kmu_shell_we.domain.season.dto.request.UpdateSeasonRequest;
import com.github.kmu_shell_we.domain.season.dto.response.SeasonResponse;
import com.github.kmu_shell_we.domain.season.service.SeasonService;
import com.github.kmu_shell_we.global.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping("/admin/seasons")
@RequiredArgsConstructor
public class AdminSeasonController {

    private final SeasonService seasonService;

    @PostMapping
    public ApiResponse<SeasonResponse> createSeason(@RequestBody @Valid CreateSeasonRequest request) {

        return ApiResponse.ok(seasonService.createSeason(request));
    }

    @PutMapping("/{seasonId}")
    public ApiResponse<SeasonResponse> updateSeason(@PathVariable UUID seasonId, @RequestBody @Valid UpdateSeasonRequest request) {

        return ApiResponse.ok(seasonService.updateSeason(seasonId, request));
    }
}
