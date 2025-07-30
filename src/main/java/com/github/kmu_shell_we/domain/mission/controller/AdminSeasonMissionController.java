package com.github.kmu_shell_we.domain.mission.controller;

import com.github.kmu_shell_we.domain.mission.dto.request.UpsertSeasonMissionRequest;
import com.github.kmu_shell_we.domain.mission.dto.response.MissionListResponse;
import com.github.kmu_shell_we.domain.mission.dto.response.MissionResponse;
import com.github.kmu_shell_we.domain.mission.service.SeasonMissionService;
import com.github.kmu_shell_we.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping("/admin/seasons/{seasonId}/missions")
@RequiredArgsConstructor
@Tag(name = "관리자용 시즌 미션 API", description = "시즌 미션 조회, 생성, 수정, 삭제를 위한 API")
public class AdminSeasonMissionController {

    private final SeasonMissionService seasonMissionService;

    @GetMapping
    @Operation(summary = "특정 시즌 전체 미션 조회", description = "특정 시즌에 속한 모든 미션의 목록을 조회합니다.")
    public ApiResponse<MissionListResponse> getSeasonMissions(
            @Parameter(description = "시즌 ID", example = "123e4567-e89b-12d3-a456-426614174000")
            @PathVariable UUID seasonId) {

        return ApiResponse.ok(seasonMissionService.getSeasonMissionsBySeasonId(seasonId));
    }


    @GetMapping("/{missionId}")
    @Operation(summary = "특정 시즌 미션 상세 조회", description = "특정 시즌에 속한 미션의 상세 정보를 조회합니다.")
    public ApiResponse<MissionResponse> getSeasonMissionById(
            @Parameter(description = "시즌 ID", example = "123e4567-e89b-12d3-a456-426614174000")
            @PathVariable UUID seasonId,
            @Parameter(description = "미션 ID", example = "123e4567-e89b-12d3-a456-426614174001")
            @PathVariable UUID missionId) {

        return ApiResponse.ok(seasonMissionService.getSeasonMissionBySeasonIdAndMissionId(seasonId, missionId));
    }

    @PostMapping("/{missionId}")
    @Operation(summary = "시즌 미션 생성", description = "특정 시즌에 새로운 미션을 추가합니다.")
    public ApiResponse<MissionResponse> createSeasonMission(
            @Parameter(description = "시즌 ID", example = "123e4567-e89b-12d3-a456-426614174000")
            @PathVariable UUID seasonId,
            @Parameter(description = "미션 ID", example = "123e4567-e89b-12d3-a456-426614174001")
            @PathVariable UUID missionId) {

        return ApiResponse.ok(seasonMissionService.createSeasonMission(seasonId, missionId));
    }

    @PutMapping("/{missionId}")
    @Operation(summary = "시즌 미션 수정", description = "특정 시즌에 속한 미션의 정보를 수정합니다.")
    public ApiResponse<MissionResponse> updateSeasonMission(
            @Parameter(description = "시즌 ID", example = "123e4567-e89b-12d3-a456-426614174000")
            @PathVariable UUID seasonId,
            @Parameter(description = "미션 ID", example = "123e4567-e89b-12d3-a456-426614174001")
            @PathVariable UUID missionId,
            @RequestBody UpsertSeasonMissionRequest request) {

        return ApiResponse.ok(seasonMissionService.updateSeasonMission(seasonId, missionId, request));
    }


    @DeleteMapping("/{missionId}")
    @Operation(summary = "시즌 미션 삭제", description = "특정 시즌에 속한 미션을 삭제합니다.")
    public ApiResponse<Void> deleteSeasonMission(
            @Parameter(description = "시즌 ID", example = "123e4567-e89b-12d3-a456-426614174000")
            @PathVariable UUID seasonId,
            @Parameter(description = "미션 ID", example = "123e4567-e89b-12d3-a456-426614174001")
            @PathVariable UUID missionId) {

        seasonMissionService.deleteSeasonMission(seasonId, missionId);
        return ApiResponse.ok();
    }
}
