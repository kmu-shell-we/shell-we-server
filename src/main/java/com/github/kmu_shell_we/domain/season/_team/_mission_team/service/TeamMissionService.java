package com.github.kmu_shell_we.domain.season._team._mission_team.service;

import com.github.kmu_shell_we.domain.mission._season_mission.entity.SeasonMission;
import com.github.kmu_shell_we.domain.mission._season_mission.repository.SeasonMissionRepository;
import com.github.kmu_shell_we.domain.mission.constant.MissionType;
import com.github.kmu_shell_we.domain.season._team._mission_team.dto.response.TeamMissionListResponse;
import com.github.kmu_shell_we.domain.season._team._mission_team.entity.TeamMission;
import com.github.kmu_shell_we.domain.season._team._mission_team.repository.TeamMissionRepository;
import com.github.kmu_shell_we.domain.season._team.entity.Team;
import com.github.kmu_shell_we.domain.season._team.repository.TeamRepository;
import com.github.kmu_shell_we.domain.season.entity.Season;
import com.github.kmu_shell_we.domain.season.exception.SeasonExceptions;
import com.github.kmu_shell_we.domain.season.repository.SeasonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
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
    @PreAuthorize("#season.isCurrentSeason() and #season == #team.season")
    public TeamMissionListResponse getTeamMissions(Season season, Team team) {

        return TeamMissionListResponse.from(teamMissionRepository
                .findAllByTeamAndEndedAtBefore(team, LocalDateTime.now()));
    }

    @Scheduled(cron = "0 0 0 * * ?")
    @Transactional
    public void assignDailyMission() {

        addRandomMission(MissionType.DAILY,
                now -> now.toLocalDate().plusDays(1).atStartOfDay());
    }

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
        if(season == null) return;

        List<SeasonMission> missions = seasonMissionRepository
                .findAllBySeasonAndMissionType(season, missionType);
        if (missions.isEmpty()) return;

        List<Team> teams = teamRepository.findAllBySeason(season);
        if (teams.isEmpty()) return;

        LocalDateTime endedAt = endCalculator.apply(now);
        ThreadLocalRandom random = ThreadLocalRandom.current();

        for (Team team : teams) {
            SeasonMission randomMission = missions.get(random.nextInt(missions.size()));
            teamMissionRepository.save(
                    TeamMission.builder()
                            .mission(randomMission.getMission())
                            .team(team)
                            .endedAt(endedAt)
                            .build()
            );
        }
    }
}
