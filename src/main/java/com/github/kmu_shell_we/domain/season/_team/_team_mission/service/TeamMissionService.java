package com.github.kmu_shell_we.domain.season._team._team_mission.service;

import com.github.kmu_shell_we.domain.mission._season_mission.entity.SeasonMission;
import com.github.kmu_shell_we.domain.mission._season_mission.repository.SeasonMissionRepository;
import com.github.kmu_shell_we.domain.mission.constant.MissionType;
import com.github.kmu_shell_we.domain.season._team._team_mission.dto.response.TeamMissionListResponse;
import com.github.kmu_shell_we.domain.season._team._team_mission.entity.TeamMission;
import com.github.kmu_shell_we.domain.season._team._team_mission.repository.TeamMissionRepository;
import com.github.kmu_shell_we.domain.season._team.entity.Team;
import com.github.kmu_shell_we.domain.season._team.repository.TeamRepository;
import com.github.kmu_shell_we.domain.season.entity.Season;
import com.github.kmu_shell_we.domain.season.exception.SeasonExceptions;
import com.github.kmu_shell_we.domain.season.repository.SeasonRepository;
import com.github.kmu_shell_we.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class TeamMissionService {

    private final TeamMissionRepository teamMissionRepository;
    private final TeamRepository teamRepository;
    private final SeasonRepository seasonRepository;
    private final SeasonMissionRepository seasonMissionRepository;

    @Transactional(readOnly = true)
    @PreAuthorize("#season.isCurrentSeason() and #season == #team.season and @teamMissionService.teamCheck(#team, authentication.principal)")
    public TeamMissionListResponse getTeamMissions(Season season, Team team) {

        return TeamMissionListResponse.from(teamMissionRepository
                .findAllByTeamAndEndedAtGreaterThanEqual(team, LocalDateTime.now()));
    }

//    @PostConstruct
    @Scheduled(cron = "0 0 0 * * ?")
    @Transactional
    public void assignDailyMission() {

        addRandomMission(MissionType.DAILY,
                now -> now.toLocalDate().plusDays(1).atStartOfDay());
    }

//    @PostConstruct
    @Scheduled(cron = "0 0 0 ? * MON")
    @Transactional
    public void assignWeeklyMission() {

        addRandomMission(MissionType.WEEKLY,
                now -> now.toLocalDate()
                        .with(TemporalAdjusters.next(DayOfWeek.SUNDAY))
                        .plusDays(1)
                        .atStartOfDay());
    }

    private void addRandomMission(
            MissionType missionType,
            Function<LocalDateTime, LocalDateTime> endCalculator
    ) {

        LocalDateTime now = LocalDateTime.now();

        Season season = seasonRepository.findCurrentSeason()
                .orElseThrow(SeasonExceptions.NOT_FOUND_SEASON::toException);

        List<SeasonMission> missions = seasonMissionRepository
                .findAllBySeasonAndMissionType(season, missionType);
        if (missions.isEmpty()) return;

        List<Team> teams = teamRepository.findAllBySeason(season);
//        if (teams.isEmpty()) return;

        LocalDateTime startedAt = season.getStartedAt();
        LocalDateTime endedAt = endCalculator.apply(now);
        ThreadLocalRandom random = ThreadLocalRandom.current();

        List<TeamMission> teamMissions = new ArrayList<>();

        for (Team team : teams) {
            SeasonMission randomMission = missions.get(random.nextInt(missions.size()));
            teamMissions.add(
                    TeamMission.builder()
                        .mission(randomMission.getMission())
                        .team(team)
                        .startedAt(startedAt)
                        .endedAt(endedAt)
                        .build()
            );
        }

        teamMissionRepository.saveAll(teamMissions);
    }

    public boolean teamCheck(Team team, User user) {

        return team.getUserTeams().stream()
                .anyMatch(userTeam -> userTeam.getUser().equals(user));
    }
}
