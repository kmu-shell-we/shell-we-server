package com.github.kmu_shell_we.domain.season._team._user_team.service;

import com.github.kmu_shell_we.domain.season._team._user_team.entity.UserTeam;
import com.github.kmu_shell_we.domain.season._team._user_team.exception.UserTeamExceptions;
import com.github.kmu_shell_we.domain.season._team._user_team.repository.UserTeamRepository;
import com.github.kmu_shell_we.domain.season._team.entity.Team;
import com.github.kmu_shell_we.domain.season.entity.Season;
import com.github.kmu_shell_we.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminUserTeamService {

    private final UserTeamRepository userTeamRepository;

    @Transactional
    @PreAuthorize("@userTeamRepository.findByUserAndTeam(#user, #team).isEmpty() and #season == #team.season")
    public void addMember(Season season, Team team, User user) {

        userTeamRepository.save(
                UserTeam.builder()
                        .user(user)
                        .team(team)
                        .build()
        );
    }

    @Transactional
    @PreAuthorize("@userTeamRepository.findByUserAndTeam(#user, #team).isPresent() and #season == #team.season")
    public void deleteMember(Season season, Team team, User user) {

        UserTeam userTeam = userTeamRepository.findByUserAndTeam(user, team)
                        .orElseThrow(UserTeamExceptions.NOT_FOUND_USER_TEAM::toException);

        userTeamRepository.delete(userTeam);
    }
}
