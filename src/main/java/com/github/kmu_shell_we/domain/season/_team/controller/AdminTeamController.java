package com.github.kmu_shell_we.domain.season._team.controller;

import com.github.kmu_shell_we.domain.season._team.dto.response.GetTeamResponse;
import com.github.kmu_shell_we.domain.season._team.dto.response.TeamListResponse;
import com.github.kmu_shell_we.domain.season._team.dto.response.TeamResponse;
import com.github.kmu_shell_we.domain.season._team.entity.Team;
import com.github.kmu_shell_we.domain.season._team.service.AdminTeamService;
import com.github.kmu_shell_we.domain.season.entity.Season;
import com.github.kmu_shell_we.global.response.ApiResponse;
import com.github.kmu_shell_we.global.security.guard.AdminGuard;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AdminGuard
@RestController
@RequestMapping("/admin/seasons/{season}/teams")
@RequiredArgsConstructor
@Tag(name = "[관리자] 팀")
public class AdminTeamController {

    private final AdminTeamService adminTeamService;

    @GetMapping
    @Operation(summary = "전체 팀 목록 조회")
    public ApiResponse<TeamListResponse> getTeams(
            @Parameter(description = "시즌 ID", schema = @Schema(type = "string", format = "uuid")) @PathVariable Season season
    ) {

        return ApiResponse.ok(adminTeamService.getTeams(season));
    }

    @GetMapping("/{team}")
    @Operation(summary = "팀 상세 조회")
    public ApiResponse<GetTeamResponse> getTeam(
            @Parameter(description = "시즌 ID", schema = @Schema(type = "string", format = "uuid")) @PathVariable Season season,
            @Parameter(description = "팀 ID", schema = @Schema(type = "string", format = "uuid")) @PathVariable Team team
    ) {

        return ApiResponse.ok(adminTeamService.getTeam(season, team));
    }

    @PostMapping
    @Operation(summary = "팀 생성")
    public ApiResponse<TeamResponse> createTeam(
            @Parameter(description = "시즌 ID", schema = @Schema(type = "string", format = "uuid")) @PathVariable Season season
    ) {

        return ApiResponse.ok(adminTeamService.createTeam(season));
    }

    @DeleteMapping("/{team}")
    @Operation(summary = "팀 삭제")
    public ApiResponse<Void> deleteTeam(
            @Parameter(description = "시즌 ID", schema = @Schema(type = "string", format = "uuid")) @PathVariable Season season,
            @Parameter(description = "팀 ID", schema = @Schema(type = "string", format = "uuid")) @PathVariable Team team
    ) {

        adminTeamService.deleteTeam(season, team);

        return ApiResponse.ok();
    }
}