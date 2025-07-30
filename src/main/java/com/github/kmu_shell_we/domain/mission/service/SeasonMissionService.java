package com.github.kmu_shell_we.domain.mission.service;

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
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SeasonMissionService {

    private final SeasonMissionRepository seasonMissionRepository;
    private final SeasonRepository seasonRepository;
    private final MissionRepository missionRepository;

    @Transactional(readOnly = true)
    public MissionListResponse getSeasonMissionsBySeasonId(UUID seasonId) {

        Season season = seasonRepository.findById(seasonId).orElseThrow(SeasonExceptionCode.NOT_FOUND_SEASON::toException);

        List<SeasonMission> seasonMissions = seasonMissionRepository.findAllBySeason(season);

        return MissionListResponse.from(seasonMissions.stream().map(SeasonMission::getMission).toList());
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
    public void deleteSeasonMission(UUID seasonId, UUID missionId) {

        Season season = seasonRepository.findById(seasonId).orElseThrow(SeasonExceptionCode.NOT_FOUND_SEASON::toException);
        Mission mission = missionRepository.findById(missionId).orElseThrow(MissionExceptionCode.NOT_FOUND_MISSION::toException);

        SeasonMission seasonMission = seasonMissionRepository.findBySeasonAndMission(season, mission)
                .orElseThrow(MissionExceptionCode.NOT_FOUND_SEASON_MISSION::toException);

        seasonMissionRepository.delete(seasonMission);
    }
}
