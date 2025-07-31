package com.github.kmu_shell_we.domain.season._team._user_team.service;

import com.github.kmu_shell_we.domain.season._team._user_team.exception.UserTeamExceptions;
import com.github.kmu_shell_we.domain.season.entity.Season;
import com.github.kmu_shell_we.domain.season.exception.SeasonExceptions;
import com.github.kmu_shell_we.domain.season.repository.SeasonRepository;
import com.github.kmu_shell_we.domain.season._team._user_team.entity.UserTeam;
import com.github.kmu_shell_we.domain.season._team._user_team.repository.UserTeamRepository;
import com.github.kmu_shell_we.domain.season._team.entity.Team;
import com.github.kmu_shell_we.domain.season._team.exception.TeamExceptions;
import com.github.kmu_shell_we.domain.season._team.repository.TeamRepository;
import com.github.kmu_shell_we.domain.user.entity.User;
import com.github.kmu_shell_we.domain.user.exception.UserExceptionCode;
import com.github.kmu_shell_we.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminUserTeamService {

    private final UserRepository userRepository;
    private final SeasonRepository seasonRepository;
    private final TeamRepository teamRepository;
    private final UserTeamRepository userTeamRepository;

    @Transactional
    public void addMember(UUID seasonId, UUID teamId, UUID userId) {

        Season season = seasonRepository.findById(seasonId)
                .orElseThrow(SeasonExceptions.NOT_FOUND_SEASON::toException);

        Team team = teamRepository.findByIdAndSeason(teamId, season)
                .orElseThrow(TeamExceptions.NOT_FOUND_TEAM::toException);

        User user = userRepository.findById(userId)
                .orElseThrow(UserExceptionCode.NOT_FOUND_USER::toException);

        userTeamRepository.save(
                UserTeam.builder()
                        .user(user)
                        .team(team)
                        .build()
        );
    }

    @Transactional
    public void deleteMember(UUID seasonId, UUID teamId, UUID userId) {

        Season season = seasonRepository.findById(seasonId)
                .orElseThrow(SeasonExceptions.NOT_FOUND_SEASON::toException);

        Team team = teamRepository.findByIdAndSeason(teamId, season)
                .orElseThrow(TeamExceptions.NOT_FOUND_TEAM::toException);

        User user = userRepository.findById(userId)
                .orElseThrow(UserExceptionCode.NOT_FOUND_USER::toException);

        UserTeam userTeam = userTeamRepository.findByUserAndTeam(user, team)
                        .orElseThrow(UserTeamExceptions.NOT_FOUND_USER_TEAM::toException);

        userTeamRepository.delete(userTeam);
    }
}
