package com.github.kmu_shell_we.domain.team.service;

import com.github.kmu_shell_we.domain.season.entity.Season;
import com.github.kmu_shell_we.domain.season.exception.SeasonExceptionCode;
import com.github.kmu_shell_we.domain.season.repository.SeasonRepository;
import com.github.kmu_shell_we.domain.team.dto.response.GetTeamResponse;
import com.github.kmu_shell_we.domain.team.dto.response.TeamResponse;
import com.github.kmu_shell_we.domain.team.entity.Team;
import com.github.kmu_shell_we.domain.team.exception.TeamExceptions;
import com.github.kmu_shell_we.domain.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminTeamService {

    private final SeasonRepository seasonRepository;
    private final TeamRepository teamRepository;

    @Transactional(readOnly = true)
    public GetTeamResponse getTeam(UUID seasonId, UUID teamId) {

        Season season = seasonRepository.findById(seasonId)
                .orElseThrow(SeasonExceptionCode.NOT_FOUND::toException);

        Team team = teamRepository.findByIdAndSeason(teamId, season).orElseThrow(TeamExceptions.NOT_FOUND::toException);

        return GetTeamResponse.from(team);
    }

    @Transactional
    public TeamResponse createTeam(UUID seasonId) {

        Season season = seasonRepository.findById(seasonId)
                .orElseThrow(SeasonExceptionCode.NOT_FOUND::toException);

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
                .orElseThrow(SeasonExceptionCode.NOT_FOUND::toException);

        Team team = teamRepository.findByIdAndSeason(teamId, season).orElseThrow(TeamExceptions.NOT_FOUND::toException);

        teamRepository.delete(team);
    }
}
