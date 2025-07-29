package com.github.kmu_shell_we.domain.team._userTeam.service;

import com.github.kmu_shell_we.domain.season.entity.Season;
import com.github.kmu_shell_we.domain.season.exception.SeasonExceptionCode;
import com.github.kmu_shell_we.domain.season.repository.SeasonRepository;
import com.github.kmu_shell_we.domain.team._userTeam.entity.UserTeam;
import com.github.kmu_shell_we.domain.team._userTeam.repository.UserTeamRepository;
import com.github.kmu_shell_we.domain.team.entity.Team;
import com.github.kmu_shell_we.domain.team.exception.TeamExceptionCode;
import com.github.kmu_shell_we.domain.team.repository.TeamRepository;
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

    private final SeasonRepository seasonRepository;
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;
    private final UserTeamRepository userTeamRepository;

    @Transactional
    public void addMember(UUID seasonId, UUID teamId, UUID userId) {

        Season season = seasonRepository.findById(seasonId)
                .orElseThrow(SeasonExceptionCode.NOT_FOUND_SEASON::toException);

        Team team = teamRepository.findByIdAndSeason(teamId, season)
                .orElseThrow(TeamExceptionCode.NOT_FOUND_TEAM::toException);

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
                .orElseThrow(SeasonExceptionCode.NOT_FOUND_SEASON::toException);

        Team team = teamRepository.findByIdAndSeason(teamId, season)
                .orElseThrow(TeamExceptionCode.NOT_FOUND_TEAM::toException);

        User user = userRepository.findById(userId)
                .orElseThrow(UserExceptionCode.NOT_FOUND_USER::toException);

        userTeamRepository.deleteByUserAndTeam(user, team);
    }
}
