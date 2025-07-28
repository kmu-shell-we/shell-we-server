package com.github.kmu_shell_we.domain.team.service;

import com.github.kmu_shell_we.domain.season.entity.Season;
import com.github.kmu_shell_we.domain.season.exception.SeasonExceptionCode;
import com.github.kmu_shell_we.domain.season.repository.SeasonRepository;
import com.github.kmu_shell_we.domain.team.dto.response.TeamListResponse;
import com.github.kmu_shell_we.domain.team.dto.response.TeamResponse;
import com.github.kmu_shell_we.domain.team.entity.Team;
import com.github.kmu_shell_we.domain.team.exception.TeamExceptionCode;
import com.github.kmu_shell_we.domain.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminTeamService {

    private final TeamRepository teamRepository;
    private final SeasonRepository  seasonRepository;

    @Transactional(readOnly = true)
    public TeamListResponse getTeams(UUID seasonId) {

        Season season = seasonRepository.findById(seasonId)
                .orElseThrow(SeasonExceptionCode.NOT_FOUND_SEASON::toException);

        List<Team> teams = teamRepository.findBySeason(season);

        return TeamListResponse.from(teams);
    }

    @Transactional(readOnly = true)
    public TeamResponse getTeam(UUID teamId) {

        Team team = teamRepository.findById(teamId)
                .orElseThrow(TeamExceptionCode.NOT_FOUND_TEAM::toException);

        return TeamResponse.from(team);
    }

    @Transactional
    public TeamResponse createTeam(UUID seasonId) {

        Season season = seasonRepository.findById(seasonId)
                .orElseThrow(SeasonExceptionCode.NOT_FOUND_SEASON::toException);

        Team team = teamRepository.save(
                Team.builder()
                        .season(season)
                        .build()
        );

        return TeamResponse.from(team);
    }

    public void deleteTeam(UUID teamId) {

        Team team = teamRepository.findById(teamId)
                .orElseThrow(TeamExceptionCode.NOT_FOUND_TEAM::toException);

        teamRepository.delete(team);
    }
}
