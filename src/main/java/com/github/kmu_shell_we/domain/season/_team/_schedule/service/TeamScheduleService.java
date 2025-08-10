package com.github.kmu_shell_we.domain.season._team._schedule.service;

import com.github.kmu_shell_we.domain.season._team._schedule.dto.response.TeamScheduleResponse;
import com.github.kmu_shell_we.domain.season._team._schedule.dto.response.UserSchedulePair;
import com.github.kmu_shell_we.domain.season._team._user_team.entity.UserTeam;
import com.github.kmu_shell_we.domain.season._team._user_team.repository.UserTeamRepository;
import com.github.kmu_shell_we.domain.season._team.entity.Team;
import com.github.kmu_shell_we.domain.season._team.repository.TeamRepository;
import com.github.kmu_shell_we.domain.season.entity.Season;
import com.github.kmu_shell_we.domain.season.repository.SeasonRepository;
import com.github.kmu_shell_we.domain.user.entity.User;
import com.github.kmu_shell_we.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamScheduleService {

    private final UserRepository  userRepository;
    private final SeasonRepository seasonRepository;
    private final TeamRepository teamRepository;
    private final UserTeamRepository  userTeamRepository;

    @Transactional(readOnly = true)
    @PreAuthorize("#season == #team.season and @teamScheduleService.verifyUserTeam(#team, #users)")
    public TeamScheduleResponse getTeamSchedule(Season season, Team team, List<User> users) {

        return TeamScheduleResponse.of(users.stream().map(UserSchedulePair::from).toList());
    }

    public boolean verifyUserTeam(Team team, List<User> users) {

        List<UserTeam> userTeams = userTeamRepository.findByUserInAndTeam(users, team);

        return userTeams.size() == users.size();
    }
}
