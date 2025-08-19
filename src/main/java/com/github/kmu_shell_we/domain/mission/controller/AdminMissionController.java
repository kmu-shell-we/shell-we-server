package com.github.kmu_shell_we.domain.mission.controller;


import com.github.kmu_shell_we.domain.mission.dto.request.UpsertMissionRequest;
import com.github.kmu_shell_we.domain.mission.dto.response.MissionListResponse;
import com.github.kmu_shell_we.domain.mission.dto.response.MissionResponse;
import com.github.kmu_shell_we.domain.mission.entity.Mission;
import com.github.kmu_shell_we.domain.mission.service.AdminMissionService;
import com.github.kmu_shell_we.global.response.ApiResponse;
import com.github.kmu_shell_we.global.security.guard.AdminGuard;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AdminGuard
@RestController
@RequestMapping("/admin/missions")
@RequiredArgsConstructor
@Tag(name = "[관리자] 글로벌 미션")
public class AdminMissionController {

    private final AdminMissionService adminMissionService;

    @GetMapping
    @Operation(summary = "전체 미션 목록 조회")
    public ApiResponse<MissionListResponse> getMissions() {

        return ApiResponse.ok(adminMissionService.getMissions());
    }

    @GetMapping("/{mission}")
    @Operation(summary = "미션 상세 조회")
    public ApiResponse<MissionResponse> getMissionBySeasonId(
            @Parameter(description = "미션 ID", schema = @Schema(type = "string", format = "uuid")) @PathVariable Mission mission
    ) {

        return ApiResponse.ok(adminMissionService.getMission(mission));
    }

    @PostMapping
    @Operation(summary = "미션 생성")
    public ApiResponse<MissionResponse> createMission(@RequestBody @Valid UpsertMissionRequest request) {

        return ApiResponse.ok(adminMissionService.createMission(request));
    }

    @PutMapping("/{mission}")
    @Operation(summary = "미션 수정")
    public ApiResponse<MissionResponse> updateMission(
            @Parameter(description = "미션 ID", schema = @Schema(type = "string", format = "uuid")) @PathVariable Mission mission,
            @RequestBody @Valid UpsertMissionRequest request
    ) {

        return ApiResponse.ok(adminMissionService.updateMission(mission, request));
    }

    @DeleteMapping("/{mission}")
    @Operation(summary = "미션 삭제")
    public ApiResponse<Void> deleteMission(
            @Parameter(description = "미션 ID", schema = @Schema(type = "string", format = "uuid")) @PathVariable Mission mission
    ) {

        adminMissionService.deleteMission(mission);

        return ApiResponse.ok();
    }
}
