package com.github.kmu_shell_we.domain.team.service;

import com.github.kmu_shell_we.domain.season.entity.Season;
import com.github.kmu_shell_we.domain.season.exception.SeasonExceptionCode;
import com.github.kmu_shell_we.domain.season.repository.SeasonRepository;
import com.github.kmu_shell_we.domain.team.dto.response.GetOtherTeamResponse;
import com.github.kmu_shell_we.domain.team.dto.response.GetTeamResponse;
import com.github.kmu_shell_we.domain.team.dto.response.TeamListResponse;
import com.github.kmu_shell_we.domain.team.entity.Team;
import com.github.kmu_shell_we.domain.team.exception.TeamExceptions;
import com.github.kmu_shell_we.domain.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final SeasonRepository seasonRepository;
    private final TeamRepository teamRepository;

    @Transactional(readOnly = true)
    public TeamListResponse getTeams(UUID seasonId) {

        Season season = seasonRepository.findById(seasonId)
                .orElseThrow(SeasonExceptionCode.NOT_FOUND::toException);

        List<Team> teams = teamRepository.findAllBySeason(season);

        return TeamListResponse.from(teams);
    }

    // TODO: 팀 멤버 연결 후, 실제 멤버 받아오기
    public GetTeamResponse getMyTeam(UUID seasonId) {

        Season season = seasonRepository.findById(seasonId)
                .orElseThrow(SeasonExceptionCode.NOT_FOUND::toException);

        return null;
    }

    public GetOtherTeamResponse getOtherTeam(UUID seasonId, UUID teamId) {

        Season season = seasonRepository.findById(seasonId)
                .orElseThrow(SeasonExceptionCode.NOT_FOUND::toException);

        Team team = teamRepository.findByIdAndSeason(teamId, season).orElseThrow(TeamExceptions.NOT_FOUND::toException);

        return GetOtherTeamResponse.from(team);
    }
}
