package com.github.kmu_shell_we.domain.season.controller;

import com.github.kmu_shell_we.domain.season.dto.request.CreateSeasonRequest;
import com.github.kmu_shell_we.domain.season.dto.response.SeasonResponse;
import com.github.kmu_shell_we.domain.season.service.SeasonService;
import com.github.kmu_shell_we.global.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping("/admin/seasons")
@RequiredArgsConstructor
public class AdminSeasonController {

    private final SeasonService seasonService;

    @PostMapping
    public ApiResponse<SeasonResponse> createSeason(@RequestBody @Valid CreateSeasonRequest request) {
        // 시즌 생성 <파이프라인>
        // 사용자 -> requestBody -> CreateSeasonRequest dto -> here
        // Season entity 생성 -> Repository 저장 -> 성공적으로 저장되었다면 SeasonResponse
        return ApiResponse.ok(seasonService.createSeason(request));
    }
}
