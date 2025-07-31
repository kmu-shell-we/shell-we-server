package com.github.kmu_shell_we.domain.season._team.service;

import com.github.kmu_shell_we.domain.season._team._user_team.exception.UserTeamExceptions;
import com.github.kmu_shell_we.domain.season._team.entity.Team;
import com.github.kmu_shell_we.domain.season._team.exception.TeamExceptions;
import com.github.kmu_shell_we.domain.season._team.repository.TeamRepository;
import com.github.kmu_shell_we.domain.season.entity.Season;
import com.github.kmu_shell_we.domain.season.exception.SeasonExceptions;
import com.github.kmu_shell_we.domain.season.repository.SeasonRepository;
import com.github.kmu_shell_we.domain.season._team.dto.response.*;
import com.github.kmu_shell_we.domain.user.entity.User;
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
                .orElseThrow(SeasonExceptions.NOT_FOUND_SEASON::toException);

        List<Team> teams = teamRepository.findAllBySeason(season);

        return TeamListResponse.from(teams);
    }

    @Transactional(readOnly = true)
    public GetTeamResponse getMyTeam(UUID seasonId, User user) {

        Season season = seasonRepository.findById(seasonId)
                .orElseThrow(SeasonExceptions.NOT_FOUND_SEASON::toException);

        // LazyInitializationException이 발생해서 수정했습니다.
        // TeamRepository를 확인해 주세요.
//        Team team = user.getUserTeams().stream()
//                .filter(userTeam1 -> userTeam1.getTeam().getSeason().equals(season))
//                .findFirst()
//                .orElseThrow(UserTeamExceptions.NOT_FOUND_USER_TEAM::toException)
//                .getTeam();

        Team team = teamRepository.findByUserAndSeason(user, season)
                .orElseThrow(UserTeamExceptions.NOT_FOUND_USER_TEAM::toException);

        return GetTeamResponse.from(team);
    }

    @Transactional(readOnly = true)
    public GetOtherTeamResponse getOtherTeam(UUID seasonId, UUID teamId) {

        Season season = seasonRepository.findById(seasonId)
                .orElseThrow(SeasonExceptions.NOT_FOUND_SEASON::toException);

        Team team = teamRepository.findByIdAndSeason(teamId, season)
                .orElseThrow(TeamExceptions.NOT_FOUND_TEAM::toException);

        return GetOtherTeamResponse.from(team);
    }
}