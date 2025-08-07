package com.github.kmu_shell_we.domain.user._schedule.controller;

import com.github.kmu_shell_we.domain.user._schedule.dto.response.ScheduleResponse;
import com.github.kmu_shell_we.domain.user._schedule.service.ScheduleService;
import com.github.kmu_shell_we.domain.user.entity.User;
import com.github.kmu_shell_we.global.response.ApiResponse;
import com.github.kmu_shell_we.global.security.guard.MemberGuard;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@MemberGuard
@RestController
@RequestMapping("/users/me/schedules")
@RequiredArgsConstructor
@Tag(name = "스케줄")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping
    @Operation(summary = "내 시간표 조회")
    public ApiResponse<ScheduleResponse> getMySchedule(@AuthenticationPrincipal User user) {

        return ApiResponse.ok(scheduleService.getMySchedule(user));
    }

    @PostMapping("/{identifier}")
    @Operation(summary = "내 스케줄 등록")
    public ApiResponse<ScheduleResponse> upsertMySchedule(
            @AuthenticationPrincipal User user,
            @PathVariable String identifier
    ) {

        return ApiResponse.ok(scheduleService.upsertMySchedule(user, identifier));
    }
}
