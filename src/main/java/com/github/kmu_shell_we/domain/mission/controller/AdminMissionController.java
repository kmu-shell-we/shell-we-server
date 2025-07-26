package com.github.kmu_shell_we.domain.mission.controller;


import com.github.kmu_shell_we.domain.mission.dto.request.UpsertMissionRequest;
import com.github.kmu_shell_we.domain.mission.dto.response.MissionListResponse;
import com.github.kmu_shell_we.domain.mission.dto.response.MissionResponse;
import com.github.kmu_shell_we.domain.mission.service.MissionService;
import com.github.kmu_shell_we.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping("/admin/missions")
@RequiredArgsConstructor
@Tag(name = "관지라용 미션 API", description = "미션 조회, 생성, 수정, 삭제를 위한 API")
public class AdminMissionController {

    private final MissionService missionService;

    @GetMapping
    @Operation(summary = "미션 전체 조회", description = "모든 미션의 목록을 조회합니다.")
    public ApiResponse<MissionListResponse> getMissions() {

        return ApiResponse.ok(missionService.getMissions());
    }


    @GetMapping("/{missionId}")
    @Operation(summary = "미션 상세 조회", description = "특정 미션의 상세 정보를 조회합니다.")
    public ApiResponse<MissionResponse> getMissionBySeasonId(
            @Parameter(description = "미션 ID", example = "123e4567-e89b-12d3-a456-426614174000")
            @PathVariable UUID missionId) {

        return ApiResponse.ok(missionService.getMission(missionId));
    }


    @PostMapping
    @Operation(summary = "미션 생성", description = "새로운 미션을 생성합니다.")
    public ApiResponse<MissionResponse> createMission(@RequestBody @Valid UpsertMissionRequest request) {

        return ApiResponse.ok(missionService.createMission(request));
    }


    @PutMapping("/{missionId}")
    @Operation(summary = "미션 수정", description = "특정 미션의 정보를 수정합니다.")
    public ApiResponse<MissionResponse> updateMission(
            @Parameter (description = "미션 ID", example = "123e4567-e89b-12d3-a456-426614174000")
            @PathVariable UUID missionId,
            @RequestBody @Valid UpsertMissionRequest request){

        return ApiResponse.ok(missionService.updateMission(missionId, request));
    }


    @DeleteMapping("/{missionId}")
    @Operation(summary = "미션 삭제", description = "특정 미션을 삭제합니다.")
    public ApiResponse<Void> deleteMission(
            @Parameter(description = "미션 ID", example = "123e4567-e89b-12d3-a456-426614174000")
            @PathVariable UUID missionId) {

        missionService.deleteMission(missionId);

        return ApiResponse.ok();
    }
}
