package com.github.kmu_shell_we.domain.season._team.service;

import com.github.kmu_shell_we.domain.season._team.dto.response.GetTeamResponse;
import com.github.kmu_shell_we.domain.season._team.dto.response.TeamListResponse;
import com.github.kmu_shell_we.domain.season._team.dto.response.TeamResponse;
import com.github.kmu_shell_we.domain.season._team.entity.Team;
import com.github.kmu_shell_we.domain.season._team.repository.TeamRepository;
import com.github.kmu_shell_we.domain.season.entity.Season;
import com.github.kmu_shell_we.domain.season.repository.SeasonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminTeamService {

    private final SeasonRepository seasonRepository;
    private final TeamRepository teamRepository;

    @Transactional(readOnly = true)
    public TeamListResponse getTeams(Season season) {

        return TeamListResponse.from(teamRepository.findAllBySeason(season));
    }

    @Transactional(readOnly = true)
    @PreAuthorize("#season == #team.season")
    public GetTeamResponse getTeam(Season season, Team team) {

        return GetTeamResponse.from(team);
    }

    @Transactional
    public TeamResponse createTeam(Season season) {

        Team team = teamRepository.save(Team.builder().season(season).build());

        return TeamResponse.from(team);
    }

    @Transactional
    @PreAuthorize("#season == #team.season")
    public void deleteTeam(Season season, Team team) {

        teamRepository.delete(team);
    }
}