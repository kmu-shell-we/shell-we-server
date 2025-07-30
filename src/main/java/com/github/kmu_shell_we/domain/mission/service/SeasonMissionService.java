package com.github.kmu_shell_we.domain.mission.service;

import com.github.kmu_shell_we.domain.mission.dto.request.UpsertSeasonMissionRequest;
import com.github.kmu_shell_we.domain.mission.dto.response.MissionListResponse;
import com.github.kmu_shell_we.domain.mission.dto.response.MissionResponse;
import com.github.kmu_shell_we.domain.mission.entity.Mission;
import com.github.kmu_shell_we.domain.mission.entity.SeasonMission;
import com.github.kmu_shell_we.domain.mission.exception.MissionExceptionCode;
import com.github.kmu_shell_we.domain.season.exception.SeasonExceptionCode;
import com.github.kmu_shell_we.domain.mission.repository.MissionRepository;
import com.github.kmu_shell_we.domain.mission.repository.SeasonMissionRepository;
import com.github.kmu_shell_we.domain.season.entity.Season;
import com.github.kmu_shell_we.domain.season.repository.SeasonRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SeasonMissionService {

    private final SeasonMissionRepository seasonMissionRepository;
    private final SeasonRepository seasonRepository;
    private final MissionRepository missionRepository;

    @Transactional
    public MissionListResponse getSeasonMissionsBySeasonId(UUID seasonId) {

        List<SeasonMission> seasonMissions = seasonMissionRepository.findAllSeasonMissionsBySeasonId(seasonId);

        return MissionListResponse.from(seasonMissions.stream().map(SeasonMission::getMission).toList());
    }

    @Transactional
    public MissionResponse getSeasonMissionBySeasonIdAndMissionId(UUID seasonId, UUID missionId) {

        SeasonMission seasonMission = seasonMissionRepository.findBySeasonAndMission(
                seasonRepository.findById(seasonId).orElseThrow(SeasonExceptionCode.NOT_FOUND_SEASON::toException),
                missionRepository.findById(missionId).orElseThrow(MissionExceptionCode.NOT_FOUND_MISSION::toException)
                );

        return MissionResponse.from(seasonMission.getMission());
    }

    @Transactional
    public MissionResponse createSeasonMission(UUID seasonId, UUID missionId) {

        Season season = seasonRepository.findById(seasonId).orElseThrow(SeasonExceptionCode.NOT_FOUND_SEASON::toException);
        Mission mission = missionRepository.findById(missionId).orElseThrow(MissionExceptionCode.NOT_FOUND_MISSION::toException);

        SeasonMission seasonMission = seasonMissionRepository.save(
                SeasonMission.builder()
                        .season(season)
                        .mission(mission)
                        .build()
        );

        return MissionResponse.from(seasonMission.getMission());
    }

    @Transactional
    public MissionResponse updateSeasonMission(UUID seasonId, UUID missionId, UpsertSeasonMissionRequest request) {

        SeasonMission seasonMission = seasonMissionRepository.save(
                seasonMissionRepository.findBySeasonAndMission(
                        seasonRepository.findById(seasonId).orElseThrow(SeasonExceptionCode.NOT_FOUND_SEASON::toException),
                        missionRepository.findById(missionId).orElseThrow(MissionExceptionCode.NOT_FOUND_MISSION::toException)
                ).toBuilder()
                        .season(seasonRepository.findById(request.getSeasonId()).orElseThrow(SeasonExceptionCode.NOT_FOUND_SEASON::toException))
                        .mission(missionRepository.findById(request.getMissionId()).orElseThrow(MissionExceptionCode.NOT_FOUND_MISSION::toException))
                        .build()
        );

        return MissionResponse.from(seasonMission.getMission());
    }

    @Transactional
    public void deleteSeasonMission(UUID seasonId, UUID missionId) {

        SeasonMission seasonMission = seasonMissionRepository.findBySeasonAndMission(
                seasonRepository.findById(seasonId).orElseThrow(SeasonExceptionCode.NOT_FOUND_SEASON::toException),
                missionRepository.findById(missionId).orElseThrow(MissionExceptionCode.NOT_FOUND_MISSION::toException)
        );

        seasonMissionRepository.delete(seasonMission);
    }
}
