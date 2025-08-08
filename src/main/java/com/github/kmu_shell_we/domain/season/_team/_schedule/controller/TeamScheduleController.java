package com.github.kmu_shell_we.domain.season._team._schedule.controller;

import com.github.kmu_shell_we.domain.season._team._schedule.dto.response.TeamScheduleResponse;
import com.github.kmu_shell_we.domain.season._team._schedule.service.TeamScheduleService;
import com.github.kmu_shell_we.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "팀 시간표")
@RestController
@RequiredArgsConstructor
@RequestMapping("/seasons/{seasonId}/teams/{teamId}/schedules")
public class TeamScheduleController {

    private final TeamScheduleService scheduleService;

    @GetMapping
    @Operation(description = "팀 시간표 조회")
    public ApiResponse<TeamScheduleResponse> getTeamSchedule(
            @Parameter(description = "시즌 ID") @PathVariable UUID seasonId,
            @Parameter(description = "팀 ID")  @PathVariable UUID teamId,
            @RequestParam UUID[] users
            ) {

        return ApiResponse.ok(scheduleService.getTeamSchedule(seasonId, teamId, users));
    }
}
