package com.github.kmu_shell_we.domain.mission.controller;


import com.github.kmu_shell_we.domain.mission.dto.request.UpsertMissionRequest;
import com.github.kmu_shell_we.domain.mission.dto.response.MissionListResponse;
import com.github.kmu_shell_we.domain.mission.dto.response.MissionResponse;
import com.github.kmu_shell_we.domain.mission.service.SpecialMissionService;
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
@RequestMapping("/admin/missions/special")
@RequiredArgsConstructor
@Tag(name = "관지라용 특별 미션 API", description = "특별 미션 조회, 생성, 수정, 삭제를 위한 API")
public class AdminSpecialMissionController {

    private final SpecialMissionService specialMissionService;

    @GetMapping
    @Operation(summary = "특별 미션 전체 조회", description = "모든 특별 미션의 목록을 조회합니다.")
    public ApiResponse<MissionListResponse> getSpecialMissions() {

        return ApiResponse.ok(specialMissionService.getSpecialMissions());
    }

    @GetMapping("/{specialMissionId}")
    @Operation(summary = "특별 미션 상세 조회", description = "특정 특별 미션의 상세 정보를 조회합니다.")
    public ApiResponse<MissionResponse> getSpecialMissionById(
            @Parameter(description = "특별 미션 ID", example = "123e4567-e89b-12d3-a456-426614174000")
            @PathVariable UUID specialMissionId) {

        return ApiResponse.ok(specialMissionService.getSpecialMissionById(specialMissionId));
    }

    @PostMapping
    @Operation(summary = "특별 미션 생성", description = "새로운 특별 미션을 생성합니다.")
    public ApiResponse<MissionResponse> createSpecialMission(
            @Parameter(description = "특별 미션 생성 요청", required = true)
            @RequestBody @Valid UpsertMissionRequest request) {

        return ApiResponse.ok(specialMissionService.createSpecialMission(request));
    }

    @PutMapping("/{specialMissionId}")
    @Operation(summary = "특별 미션 수정", description = "특정 특별 미션의 정보를 수정합니다.")
    public ApiResponse<MissionResponse> updateSpecialMission(
            @Parameter(description = "특별 미션 ID", example = "123e4567-e89b-12d3-a456-426614174000")
            @PathVariable UUID specialMissionId,
            @Parameter(description = "특별 미션 수정 요청", required = true)
            @RequestBody @Valid UpsertMissionRequest request){

        return ApiResponse.ok(specialMissionService.updateSpecialMission(specialMissionId, request));
    }

    @DeleteMapping("/{specialMissionId}")
    @Operation(summary = "특별 미션 삭제", description = "특정 특별 미션을 삭제합니다.")
    public ApiResponse<Void> deleteSpecialMission(
            @Parameter(description = "특별 미션 ID", example = "123e4567-e89b-12d3-a456-426614174000")
            @PathVariable UUID specialMissionId) {

        specialMissionService.deleteSpecialMission(specialMissionId);

        return ApiResponse.ok();
    }
}
