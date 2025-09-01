package com.github.kmu_shell_we.domain.mission._season_mission.service;

import com.github.kmu_shell_we.domain.mission._season_mission.entity.SeasonMission;
import com.github.kmu_shell_we.domain.mission._season_mission.exception.SeasonMissionExceptions;
import com.github.kmu_shell_we.domain.mission._season_mission.repository.SeasonMissionRepository;
import com.github.kmu_shell_we.domain.mission.constant.MissionType;
import com.github.kmu_shell_we.domain.mission.dto.response.MissionListResponse;
import com.github.kmu_shell_we.domain.mission.dto.response.MissionResponse;
import com.github.kmu_shell_we.domain.mission.entity.Mission;
import com.github.kmu_shell_we.domain.season.entity.Season;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminSeasonMissionService {

    private final SeasonMissionRepository seasonMissionRepository;

    @Transactional(readOnly = true)
    public MissionListResponse getSeasonMissions(Season season) {

        return MissionListResponse.fromSeasonMission(seasonMissionRepository.findAllBySeason(season));
    }

    @Transactional(readOnly = true)
    public MissionListResponse getSeasonSpecialMissions(Season season){

        return MissionListResponse.fromSeasonMission(
                seasonMissionRepository.findAllBySeasonAndMissionType(season, MissionType.SPECIAL)
        );
    }

    @Transactional
    public MissionResponse createSeasonMission(Season season, Mission mission) {

        seasonMissionRepository.findBySeasonAndMission(season, mission)
                .ifPresent(seasonMission -> {throw SeasonMissionExceptions.ALREADY_ADDED_SEASON_MISSION.toException();});

        SeasonMission seasonMission = seasonMissionRepository.save(
                SeasonMission.builder()
                        .season(season)
                        .mission(mission)
                        .build()
        );

        return MissionResponse.from(seasonMission);
    }

    @Transactional
    public void deleteSeasonMission(Season season, Mission mission) {

        SeasonMission seasonMission = seasonMissionRepository.findBySeasonAndMission(season, mission)
                .orElseThrow(SeasonMissionExceptions.NOT_FOUND_SEASON_MISSION::toException);

        seasonMissionRepository.delete(seasonMission);
    }
}
