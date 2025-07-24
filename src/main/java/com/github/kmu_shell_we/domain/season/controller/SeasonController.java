package com.github.kmu_shell_we.domain.season.controller;

import com.github.kmu_shell_we.domain.season.dto.response.SeasonResponse;
import com.github.kmu_shell_we.domain.season.service.SeasonService;
import com.github.kmu_shell_we.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seasons")
@RequiredArgsConstructor
@Tag(name = "일반 사용자용 시즌 API", description = "시즌 조회를 위한 API")
public class SeasonController {

    private final SeasonService seasonService;

    // 현재 시즌 조회
    @GetMapping("/current")
    @Tag(name = "시즌 조회", description = "현재 시즌 정보를 조회합니다.")
    public ApiResponse<SeasonResponse> getCurrentSeason() {

        return ApiResponse.ok(seasonService.getCurrentSeason());
    }
}
