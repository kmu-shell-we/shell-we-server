package com.github.kmu_shell_we.domain.mission._season_mission.controller;

import com.github.kmu_shell_we.domain.mission._season_mission.service.AdminSeasonMissionService;
import com.github.kmu_shell_we.domain.mission.dto.response.MissionListResponse;
import com.github.kmu_shell_we.domain.mission.dto.response.MissionResponse;
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
@RequestMapping("/admin/seasons/{seasonId}/missions")
@RequiredArgsConstructor
@Tag(name = "[관지라] 시즌 미션")
public class AdminSeasonMissionController {

    private final AdminSeasonMissionService adminSeasonMissionService;

    @GetMapping
    @Operation(summary = "특정 시즌 전체 미션 조회")
    public ApiResponse<MissionListResponse> getSeasonMissions(@Parameter(description = "시즌 ID") @PathVariable UUID seasonId) {

        return ApiResponse.ok(adminSeasonMissionService.getSeasonMissions(seasonId));
    }

    @PostMapping("/{missionId}")
    @Operation(summary = "시즌 미션 생성")
    public ApiResponse<MissionResponse> createSeasonMission(
            @Parameter(description = "시즌 ID") @PathVariable UUID seasonId,
            @Parameter(description = "미션 ID") @PathVariable UUID missionId
    ) {

        return ApiResponse.ok(adminSeasonMissionService.createSeasonMission(seasonId, missionId));
    }

    @DeleteMapping("/{missionId}")
    @Operation(summary = "시즌 미션 삭제")
    public ApiResponse<Void> deleteSeasonMission(
            @Parameter(description = "시즌 ID") @PathVariable UUID seasonId,
            @Parameter(description = "미션 ID") @PathVariable UUID missionId
    ) {

        adminSeasonMissionService.deleteSeasonMission(seasonId, missionId);

        return ApiResponse.ok();
    }
}
