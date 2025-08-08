package com.github.kmu_shell_we.domain.season._team._schedule.service;

import com.github.kmu_shell_we.domain.season._team._schedule.dto.response.TeamScheduleResponse;
import com.github.kmu_shell_we.domain.season._team._user_team.exception.UserTeamExceptions;
import com.github.kmu_shell_we.domain.season._team._user_team.repository.UserTeamRepository;
import com.github.kmu_shell_we.domain.season._team.entity.Team;
import com.github.kmu_shell_we.domain.season._team.exception.TeamExceptions;
import com.github.kmu_shell_we.domain.season._team.repository.TeamRepository;
import com.github.kmu_shell_we.domain.season.entity.Season;
import com.github.kmu_shell_we.domain.season.exception.SeasonExceptions;
import com.github.kmu_shell_we.domain.season.repository.SeasonRepository;
import com.github.kmu_shell_we.domain.user._schedule.dto.response.ScheduleResponse;
import com.github.kmu_shell_we.domain.user._schedule.dto.response.TimeRange;
import com.github.kmu_shell_we.domain.user._schedule.entity.Schedule;
import com.github.kmu_shell_we.domain.user.dto.response.SimpleUserResponse;
import com.github.kmu_shell_we.domain.user.entity.User;
import com.github.kmu_shell_we.domain.user.exception.UserExceptions;
import com.github.kmu_shell_we.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.util.*;
import java.util.stream.Collectors;

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

        List<Map<SimpleUserResponse, ScheduleResponse>> schedules = new ArrayList<>();

        for (UUID userId : users) {

            User user = userRepository.findByIdWithSchedule(userId)
                    .orElseThrow(UserExceptions.NOT_FOUND_USER::toException);

            userTeamRepository.findByUserAndTeam(user, team)
                    .orElseThrow(UserTeamExceptions.NOT_FOUND_USER_TEAM::toException);

            Map<DayOfWeek, List<TimeRange>> collect = user.getSchedules().stream()
                    .collect(Collectors.groupingBy(
                            Schedule::getDayOfWeek,
                            Collectors.mapping(TimeRange::from, Collectors.toList())
                    ));

            Map<SimpleUserResponse, ScheduleResponse> map = new HashMap<>();
            map.put(SimpleUserResponse.from(user), ScheduleResponse.of(collect));
            schedules.add(map);
        }

        return TeamScheduleResponse.of(schedules);
    }
}
