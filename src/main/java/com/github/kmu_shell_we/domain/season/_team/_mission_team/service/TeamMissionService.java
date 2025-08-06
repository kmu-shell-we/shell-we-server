package com.github.kmu_shell_we.domain.season._team._mission_team.service;

import com.github.kmu_shell_we.domain.season._team._mission_team.dto.response.TeamMissionListResponse;
import com.github.kmu_shell_we.domain.season._team._mission_team.entity.TeamMission;
import com.github.kmu_shell_we.domain.season._team._mission_team.repository.TeamMissionRepository;
import com.github.kmu_shell_we.domain.season._team.entity.Team;
import com.github.kmu_shell_we.domain.season._team.exception.TeamExceptions;
import com.github.kmu_shell_we.domain.season._team.repository.TeamRepository;
import com.github.kmu_shell_we.domain.season.entity.Season;
import com.github.kmu_shell_we.domain.season.exception.SeasonExceptions;
import com.github.kmu_shell_we.domain.season.repository.SeasonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeamMissionService {

    private final TeamMissionRepository teamMissionRepository;
    private final TeamRepository teamRepository;
    private final SeasonRepository seasonRepository;

    @Transactional(readOnly = true)
    public TeamMissionListResponse getTeamMissions(UUID seasonId, UUID teamId) {

        Season season = seasonRepository.findById(seasonId)
                .orElseThrow(SeasonExceptions.NOT_FOUND_SEASON::toException);

        Team team = teamRepository.findById(teamId)
                .orElseThrow(TeamExceptions.NOT_FOUND_TEAM::toException);

        List<TeamMission> teamMissions = teamMissionRepository.findAllBySeasonAndTeam(season, team);

        return TeamMissionListResponse.from(teamMissions);
    }
}
