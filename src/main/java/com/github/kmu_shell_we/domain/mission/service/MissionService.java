package com.github.kmu_shell_we.domain.mission.service;

import com.github.kmu_shell_we.domain.mission.dto.request.UpsertMissionRequest;
import com.github.kmu_shell_we.domain.mission.dto.response.MissionListResponse;
import com.github.kmu_shell_we.domain.mission.dto.response.MissionResponse;
import com.github.kmu_shell_we.domain.mission.entity.Mission;
import com.github.kmu_shell_we.domain.mission.exception.MissionExceptionCode;
import com.github.kmu_shell_we.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;

    // 전체 미션 목록 조회
    public MissionListResponse getMissions() {

        List<Mission> missions = missionRepository.findAll();

        return MissionListResponse.from(missions);
    }

    // 미션 상세 조회
    public MissionResponse getMissionById(UUID missionId) {

        Mission mission = missionRepository.findById(missionId).orElseThrow(MissionExceptionCode.NOT_FOUND_MISSION::toException);

        return MissionResponse.from(mission);
    }

    // 미션 생성
    public MissionResponse createMission(UpsertMissionRequest request) {

        Mission savedMission = missionRepository.save(
                Mission.builder()
                        .name(request.getName())
                        .reward(request.getReward())
                        .build()
        );

        return MissionResponse.from(savedMission);
    }


    // 미션 수정
    public MissionResponse updateMission(UUID missionId, UpsertMissionRequest request) {

        Mission mission = missionRepository.findById(missionId).orElseThrow(MissionExceptionCode.NOT_FOUND_MISSION::toException);

        mission.setName(request.getName());
        mission.setReward(request.getReward());

        return MissionResponse.from(missionRepository.save(mission));
    }

    // 미션 삭제
    public void deleteMission(UUID missionId) {

        Mission mission = missionRepository.findById(missionId).orElseThrow(MissionExceptionCode.NOT_FOUND_MISSION::toException);

        missionRepository.delete(mission);
    }
}
