package com.github.kmu_shell_we.domain.season._team._item.service;

import com.github.kmu_shell_we.domain.mission._season_mission.exception.SeasonMissionExceptions;
import com.github.kmu_shell_we.domain.mission._season_mission.repository.SeasonMissionRepository;
import com.github.kmu_shell_we.domain.mission.entity.Mission;
import com.github.kmu_shell_we.domain.season._team._item.dto.response.detail.MissionChangeResponse;
import com.github.kmu_shell_we.domain.season._team._mission_team.exceptions.TeamMissionExceptions;
import com.github.kmu_shell_we.domain.season._team._mission_team.repository.TeamMissionRepository;
import com.github.kmu_shell_we.domain.season._team.entity.Team;
import com.github.kmu_shell_we.domain.season.entity.Season;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MissionChangeService {

    private final TeamMissionRepository teamMissionRepository;
    private final SeasonMissionRepository  seasonMissionRepository;

    private Mission getCurrentMissionFromTeam(Team team) {

        return teamMissionRepository
                .findAllByTeamAndEndedAtBefore(team, LocalDateTime.now())
                .getFirst()
                .getMission();
    }

    private Mission generateNewMission(Season season) {

        return seasonMissionRepository.findRandomOneBySeason(season)
                .orElseThrow(SeasonMissionExceptions.NOT_FOUND_SEASON_MISSION::toException)
                .getMission();
    }

    @PreAuthorize("#season.isCurrentSeason() and #season == #team.season")
    public MissionChangeResponse changeToNewMission(Season season, Team team) {

        Mission previous = getCurrentMissionFromTeam(team);

        Mission after = generateNewMission(season);

        teamMissionRepository.findByTeamAndMission(team, previous)
                .orElseThrow(TeamMissionExceptions.NOT_FOUND_TEAM_MISSION::toException)
                .toBuilder()
                .mission(after)
                .build();

        return MissionChangeResponse.of(previous, after);
    }
}
