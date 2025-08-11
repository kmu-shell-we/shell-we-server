package com.github.kmu_shell_we.domain.season.controller;

import com.github.kmu_shell_we.domain.season.dto.response.SeasonResponse;
import com.github.kmu_shell_we.domain.season.service.SeasonService;
import com.github.kmu_shell_we.global.response.ApiResponse;
import com.github.kmu_shell_we.global.security.guard.MemberGuard;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
