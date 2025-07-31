package com.github.kmu_shell_we.domain.season._team.service;

import com.github.kmu_shell_we.domain.season._team.entity.Team;
import com.github.kmu_shell_we.domain.season._team.exception.TeamExceptions;
import com.github.kmu_shell_we.domain.season._team.repository.TeamRepository;
import com.github.kmu_shell_we.domain.season.entity.Season;
import com.github.kmu_shell_we.domain.season.exception.SeasonExceptions;
import com.github.kmu_shell_we.domain.season.repository.SeasonRepository;
import com.github.kmu_shell_we.domain.season._team.dto.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminTeamService {

    private final SeasonRepository seasonRepository;
    private final TeamRepository teamRepository;

    @Transactional(readOnly = true)
    public TeamListResponse getTeams(UUID seasonId) {

        Season season = seasonRepository.findById(seasonId)
                .orElseThrow(SeasonExceptions.NOT_FOUND_SEASON::toException);

        List<Team> teams = teamRepository.findAllBySeason(season);

        return TeamListResponse.from(teams);
    }

    @Transactional(readOnly = true)
    public GetTeamResponse getTeam(UUID seasonId, UUID teamId) {

        Season season = seasonRepository.findById(seasonId)
                .orElseThrow(SeasonExceptions.NOT_FOUND_SEASON::toException);

        Team team = teamRepository.findByIdAndSeason(teamId, season)
                .orElseThrow(TeamExceptions.NOT_FOUND_TEAM::toException);

        return GetTeamResponse.from(team);
    }

    @Transactional
    public TeamResponse createTeam(UUID seasonId) {

        Season season = seasonRepository.findById(seasonId)
                .orElseThrow(SeasonExceptions.NOT_FOUND_SEASON::toException);

        Team team = teamRepository.save(
                Team.builder()
                        .season(season)
                        .build()
        );

        return TeamResponse.from(team);
    }

    @Transactional
    public void deleteTeam(UUID seasonId, UUID teamId) {

        Season season = seasonRepository.findById(seasonId)
                .orElseThrow(SeasonExceptions.NOT_FOUND_SEASON::toException);

        Team team = teamRepository.findByIdAndSeason(teamId, season)
                .orElseThrow(TeamExceptions.NOT_FOUND_TEAM::toException);

        teamRepository.delete(team);
    }
}