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

    public MissionListResponse getMissions() {

        List<Mission> missions = missionRepository.findAll();

        return MissionListResponse.from(missions);
    }

    public MissionResponse getMission(UUID missionId) {

        Mission mission = missionRepository.findById(missionId).orElseThrow(MissionExceptionCode.NOT_FOUND_MISSION::toException);

        return MissionResponse.from(mission);
    }

    public MissionResponse createMission(UpsertMissionRequest request) {

        Mission savedMission = missionRepository.save(
                Mission.builder()
                        .name(request.getName())
                        .reward(request.getReward())
                        .build()
        );

        return MissionResponse.from(savedMission);
    }


    public MissionResponse updateMission(UUID missionId, UpsertMissionRequest request) {

        Mission mission = missionRepository.save(
                missionRepository.findById(missionId)
                        .orElseThrow(MissionExceptionCode.NOT_FOUND_MISSION::toException)
                        .toBuilder()
                        .name(request.getName())
                        .reward(request.getReward())
                        .build()
        );

        return MissionResponse.from(mission);
    }

    public void deleteMission(UUID missionId) {

        Mission mission = missionRepository.findById(missionId).orElseThrow(MissionExceptionCode.NOT_FOUND_MISSION::toException);

        missionRepository.delete(mission);
    }
}
