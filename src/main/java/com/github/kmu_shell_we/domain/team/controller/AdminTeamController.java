package com.github.kmu_shell_we.domain.team.controller;

import com.github.kmu_shell_we.domain.team.dto.response.GetTeamResponse;
import com.github.kmu_shell_we.domain.team.dto.response.TeamResponse;
import com.github.kmu_shell_we.domain.team.service.AdminTeamService;
import com.github.kmu_shell_we.global.response.ApiResponse;
import com.github.kmu_shell_we.global.security.guard.AdminGuard;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AdminGuard
@RestController
@RequestMapping("/admin/seasons/{seasonId}/teams")
@RequiredArgsConstructor
@Tag(name = "[관리자] 팀")
public class AdminTeamController {

    private final AdminTeamService adminTeamService;

    @GetMapping("/{teamId}")
    @Operation(summary = "팀 상세 조회")
    public ApiResponse<GetTeamResponse> getTeam(
            @Parameter(description = "시즌 ID") @PathVariable UUID seasonId,
            @Parameter(description = "팀 ID") @PathVariable UUID teamId
    ) {

        return ApiResponse.ok(adminTeamService.getTeam(seasonId, teamId));
    }

    @PostMapping
    @Operation(summary = "팀 생성")
    public ApiResponse<TeamResponse> createTeam(@Parameter(description = "시즌 ID") @PathVariable UUID seasonId) {

        return ApiResponse.ok(adminTeamService.createTeam(seasonId));
    }

    @DeleteMapping("/{teamId}")
    @Operation(summary = "팀 삭제")
    public ApiResponse<Void> deleteTeam(
            @Parameter(description = "시즌 ID") @PathVariable UUID seasonId,
            @Parameter(description = "팀 ID") @PathVariable UUID teamId
    ) {

        adminTeamService.deleteTeam(seasonId, teamId);

        return ApiResponse.ok();
    }
}
