package com.github.kmu_shell_we.domain.mission.service;

import com.github.kmu_shell_we.domain.mission.dto.request.UpsertMissionRequest;
import com.github.kmu_shell_we.domain.mission.dto.response.MissionListResponse;
import com.github.kmu_shell_we.domain.mission.dto.response.MissionResponse;
import com.github.kmu_shell_we.domain.mission.entity.Mission;
import com.github.kmu_shell_we.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminMissionService {

    private final MissionRepository missionRepository;

    @Transactional(readOnly = true)
    public MissionListResponse getMissions() {

        return MissionListResponse.from(missionRepository.findAll());
    }

    @Transactional(readOnly = true)
    public MissionResponse getMission(Mission mission) {

        return MissionResponse.from(mission);
    }

    @Transactional
    public MissionResponse createMission(UpsertMissionRequest request) {

        Mission mission = missionRepository.save(
                Mission.builder()
                        .name(request.getName())
                        .reward(request.getReward())
                        .description(request.getDescription())
                        .type(request.getType())
                        .build()
        );

        return MissionResponse.from(mission);
    }

    @Transactional
    public MissionResponse updateMission(Mission mission, UpsertMissionRequest request) {

        mission = missionRepository.save(
                mission.toBuilder()
                        .name(request.getName())
                        .reward(request.getReward())
                        .description(request.getDescription())
                        .type(request.getType())
                        .build()
        );

        return MissionResponse.from(mission);
    }

    @Transactional
    public void deleteMission(Mission mission) {

        missionRepository.delete(mission);
    }
}
