package com.github.kmu_shell_we.domain.season._team.service;

import com.github.kmu_shell_we.domain.season._team._user_team.exception.UserTeamExceptions;
import com.github.kmu_shell_we.domain.season._team.dto.response.GetOtherTeamResponse;
import com.github.kmu_shell_we.domain.season._team.dto.response.GetTeamResponse;
import com.github.kmu_shell_we.domain.season._team.dto.response.TeamListResponse;
import com.github.kmu_shell_we.domain.season._team.entity.Team;
import com.github.kmu_shell_we.domain.season._team.repository.TeamRepository;
import com.github.kmu_shell_we.domain.season.entity.Season;
import com.github.kmu_shell_we.domain.season.repository.SeasonRepository;
import com.github.kmu_shell_we.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final SeasonRepository seasonRepository;
    private final TeamRepository teamRepository;

    @Transactional(readOnly = true)
    @PreAuthorize("#season.isCurrentSeason()")
    public TeamListResponse getTeams(Season season) {

        return TeamListResponse.from(teamRepository.findAllBySeason(season));
    }

    @Transactional(readOnly = true)
    @PreAuthorize("#season.isCurrentSeason()")
    public GetTeamResponse getMyTeam(User user, Season season) {

        Team team = teamRepository.findByUserAndSeason(user, season)
                .orElseThrow(UserTeamExceptions.NOT_FOUND_USER_TEAM::toException);

        return GetTeamResponse.from(team);
    }

    @Transactional(readOnly = true)
    @PreAuthorize("#season.isCurrentSeason() and #season == #team.season")
    public GetOtherTeamResponse getOtherTeam(Season season, Team team) {

        return GetOtherTeamResponse.from(team);
    }
}