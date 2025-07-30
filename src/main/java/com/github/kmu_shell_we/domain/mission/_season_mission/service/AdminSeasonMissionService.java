package com.github.kmu_shell_we.domain.mission._season_mission.service;

import com.github.kmu_shell_we.domain.mission._season_mission.entity.SeasonMission;
import com.github.kmu_shell_we.domain.mission._season_mission.exception.SeasonMissionExceptions;
import com.github.kmu_shell_we.domain.mission._season_mission.repository.SeasonMissionRepository;
import com.github.kmu_shell_we.domain.mission.dto.response.MissionListResponse;
import com.github.kmu_shell_we.domain.mission.dto.response.MissionResponse;
import com.github.kmu_shell_we.domain.mission.entity.Mission;
import com.github.kmu_shell_we.domain.mission.exception.MissionExceptions;
import com.github.kmu_shell_we.domain.mission.repository.MissionRepository;
import com.github.kmu_shell_we.domain.season.entity.Season;
import com.github.kmu_shell_we.domain.season.exception.SeasonExceptionCode;
import com.github.kmu_shell_we.domain.season.repository.SeasonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminSeasonMissionService {

    private final MissionRepository missionRepository;
    private final SeasonRepository seasonRepository;
    private final SeasonMissionRepository seasonMissionRepository;

    @Transactional(readOnly = true)
    public MissionListResponse getSeasonMissions(UUID seasonId) {

        Season season = seasonRepository.findById(seasonId)
                .orElseThrow(SeasonExceptionCode.NOT_FOUND::toException);

        List<SeasonMission> seasonMissions = seasonMissionRepository.findAllBySeason(season);

        return MissionListResponse.fromSeasonMission(seasonMissions);
    }

    @Transactional
    public MissionResponse createSeasonMission(UUID seasonId, UUID missionId) {

        Season season = seasonRepository.findById(seasonId)
                .orElseThrow(SeasonExceptionCode.NOT_FOUND::toException);

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(MissionExceptions.NOT_FOUND::toException);

        SeasonMission seasonMission = seasonMissionRepository.save(
                SeasonMission.builder()
                        .season(season)
                        .mission(mission)
                        .build()
        );

        return MissionResponse.from(seasonMission);
    }

    @Transactional
    public void deleteSeasonMission(UUID seasonId, UUID missionId) {

        Season season = seasonRepository.findById(seasonId)
                .orElseThrow(SeasonExceptionCode.NOT_FOUND::toException);

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(MissionExceptions.NOT_FOUND::toException);

        SeasonMission seasonMission = seasonMissionRepository.findBySeasonAndMission(season, mission)
                .orElseThrow(SeasonMissionExceptions.NOT_FOUND::toException);

        seasonMissionRepository.delete(seasonMission);
    }
}
