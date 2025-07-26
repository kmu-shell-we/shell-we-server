package com.github.kmu_shell_we.domain.mission.service;

import com.github.kmu_shell_we.domain.mission.dto.request.UpsertMissionRequest;
import com.github.kmu_shell_we.domain.mission.dto.response.MissionListResponse;
import com.github.kmu_shell_we.domain.mission.dto.response.MissionResponse;
import com.github.kmu_shell_we.domain.mission.entity.Mission;
import com.github.kmu_shell_we.domain.mission.entity.SpecialMission;
import com.github.kmu_shell_we.domain.mission.exception.MissionExceptionCode;
import com.github.kmu_shell_we.domain.mission.repository.SpecialMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SpecialMissionService {

    private final SpecialMissionRepository specialMissionRepository;

    public MissionListResponse getSpecialMissions() {

         List<SpecialMission> specialMissions = specialMissionRepository.findAll();
         List<Mission> missions = new ArrayList<>(specialMissions);

         return MissionListResponse.from(missions);
    }

    public MissionResponse getSpecialMissionById(UUID specialMissionId) {

        Mission specialMission = specialMissionRepository.findById(specialMissionId).orElseThrow(MissionExceptionCode.NOT_FOUND_SPECIAL_MISSION::toException);

        return MissionResponse.from(specialMission);
    }

    public MissionResponse createSpecialMission(UpsertMissionRequest request) {

        SpecialMission specialMission = specialMissionRepository.save(
                SpecialMission.builder()
                        .name(request.getName())
                        .reward(request.getReward())
                        .build()
        );

        return MissionResponse.from(specialMissionRepository.save(specialMission));
    }

    public MissionResponse updateSpecialMission(UUID specialMissionId, UpsertMissionRequest request) {

        SpecialMission specialMission = specialMissionRepository.save(
                specialMissionRepository.findById(specialMissionId)
                        .orElseThrow(MissionExceptionCode.NOT_FOUND_SPECIAL_MISSION::toException)
                        .toBuilder()
                        .name(request.getName())
                        .reward(request.getReward())
                        .build()
        );

        return MissionResponse.from(specialMission);
    }

    public void deleteSpecialMission(UUID specialMissionId){

        SpecialMission specialMission = specialMissionRepository.findById(specialMissionId).orElseThrow(MissionExceptionCode.NOT_FOUND_SPECIAL_MISSION::toException);

        specialMissionRepository.delete(specialMission);
    }
}
