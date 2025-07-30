package com.github.kmu_shell_we.domain.mission.service;

import com.github.kmu_shell_we.domain.mission.dto.request.UpsertMissionRequest;
import com.github.kmu_shell_we.domain.mission.dto.response.MissionListResponse;
import com.github.kmu_shell_we.domain.mission.dto.response.MissionResponse;
import com.github.kmu_shell_we.domain.mission.entity.Mission;
import com.github.kmu_shell_we.domain.mission.exception.MissionExceptions;
import com.github.kmu_shell_we.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminMissionService {

    private final MissionRepository missionRepository;

    @Transactional(readOnly = true)
    public MissionListResponse getMissions() {

        List<Mission> missions = missionRepository.findAll();

        return MissionListResponse.from(missions);
    }

    @Transactional(readOnly = true)
    public MissionResponse getMission(UUID missionId) {

        Mission mission = missionRepository.findById(missionId).orElseThrow(MissionExceptions.NOT_FOUND::toException);

        return MissionResponse.from(mission);
    }

    @Transactional
    public MissionResponse createMission(UpsertMissionRequest request) {

        Mission savedMission = missionRepository.save(
                Mission.builder()
                        .name(request.getName())
                        .reward(request.getReward())
                        .build()
        );

        return MissionResponse.from(savedMission);
    }

    @Transactional
    public MissionResponse updateMission(UUID missionId, UpsertMissionRequest request) {

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(MissionExceptions.NOT_FOUND::toException)
                .toBuilder()
                .name(request.getName())
                .reward(request.getReward())
                .build();

        return MissionResponse.from(mission);
    }

    @Transactional
    public void deleteMission(UUID missionId) {

        Mission mission = missionRepository.findById(missionId).orElseThrow(MissionExceptions.NOT_FOUND::toException);

        missionRepository.delete(mission);
    }
}
