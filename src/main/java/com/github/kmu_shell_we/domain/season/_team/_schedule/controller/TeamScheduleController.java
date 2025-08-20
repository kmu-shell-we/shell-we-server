package com.github.kmu_shell_we.domain.season._team._schedule.controller;

import com.github.kmu_shell_we.domain.season._team._schedule.dto.response.TeamScheduleResponse;
import com.github.kmu_shell_we.domain.season._team._schedule.service.TeamScheduleService;
import com.github.kmu_shell_we.domain.season._team.entity.Team;
import com.github.kmu_shell_we.domain.season.entity.Season;
import com.github.kmu_shell_we.domain.user.entity.User;
import com.github.kmu_shell_we.global.response.ApiResponse;
import com.github.kmu_shell_we.global.security.guard.MemberGuard;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@MemberGuard
@Tag(name = "팀 시간표")
@RestController
@RequiredArgsConstructor
@RequestMapping("/seasons/{season}/teams/{team}/schedules")
public class TeamScheduleController {

    private final TeamScheduleService scheduleService;

    @GetMapping
    @Operation(description = "팀 시간표 조회")
    public ApiResponse<TeamScheduleResponse> getTeamSchedule(
            @Parameter(description = "시즌 ID", schema = @Schema(type = "string", format = "uuid")) @PathVariable Season season,
            @Parameter(description = "팀 ID", schema = @Schema(type = "string", format = "uuid"))  @PathVariable Team team,
            @RequestParam List<User> users
    ) {

        return ApiResponse.ok(scheduleService.getTeamSchedule(season, team, users));
    }
}
