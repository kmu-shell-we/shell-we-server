package com.github.kmu_shell_we.domain.season._team._schedule.service;

import com.github.kmu_shell_we.domain.season._team._schedule.dto.response.TeamScheduleResponse;
import com.github.kmu_shell_we.domain.season._team._schedule.dto.response.UserSchedulePair;
import com.github.kmu_shell_we.domain.season._team._user_team.exception.UserTeamExceptions;
import com.github.kmu_shell_we.domain.season._team._user_team.repository.UserTeamRepository;
import com.github.kmu_shell_we.domain.season._team.entity.Team;
import com.github.kmu_shell_we.domain.season._team.exception.TeamExceptions;
import com.github.kmu_shell_we.domain.season._team.repository.TeamRepository;
import com.github.kmu_shell_we.domain.season.entity.Season;
import com.github.kmu_shell_we.domain.season.exception.SeasonExceptions;
import com.github.kmu_shell_we.domain.season.repository.SeasonRepository;
import com.github.kmu_shell_we.domain.user.entity.User;
import com.github.kmu_shell_we.domain.user.exception.UserExceptions;
import com.github.kmu_shell_we.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeamScheduleService {

    private final UserRepository  userRepository;
    private final SeasonRepository seasonRepository;
    private final TeamRepository teamRepository;
    private final UserTeamRepository  userTeamRepository;

    @Transactional(readOnly = true)
    public TeamScheduleResponse getTeamSchedule(UUID seasonId, UUID teamId, UUID[] users) {

        Season season = seasonRepository.findById(seasonId)
                .orElseThrow(SeasonExceptions.NOT_FOUND_SEASON::toException);

        Team team = teamRepository.findByIdAndSeason(teamId, season)
                .orElseThrow(TeamExceptions.NOT_FOUND_TEAM::toException);

        List<UserSchedulePair> userSchedule = new ArrayList<>();

        for (UUID userId : users) {

            User user = userRepository.findByIdWithSchedule(userId)
                    .orElseThrow(UserExceptions.NOT_FOUND_USER::toException);

            userTeamRepository.findByUserAndTeam(user, team)
                    .orElseThrow(UserTeamExceptions.NOT_FOUND_USER_TEAM::toException);

            userSchedule.add(UserSchedulePair.from(user, user.getSchedules()));
        }

        return TeamScheduleResponse.of(userSchedule);
    }
}
