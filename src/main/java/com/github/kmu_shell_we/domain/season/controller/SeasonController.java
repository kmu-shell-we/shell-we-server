package com.github.kmu_shell_we.domain.season.controller;

import com.github.kmu_shell_we.domain.season.dto.response.SeasonResponse;
import com.github.kmu_shell_we.domain.season.service.SeasonService;
import com.github.kmu_shell_we.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seasons")
@RequiredArgsConstructor
public class SeasonController {

    private final SeasonService seasonService;

    // 현재 시즌 조회
    @GetMapping("/current")
    public ApiResponse<SeasonResponse> getCurrentSeason() {

        return ApiResponse.ok(seasonService.getCurrentSeason());
    }
}
