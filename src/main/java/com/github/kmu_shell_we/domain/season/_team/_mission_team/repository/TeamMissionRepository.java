package com.github.kmu_shell_we.domain.season._team._mission_team.repository;

import com.github.kmu_shell_we.domain.mission.entity.Mission;
import com.github.kmu_shell_we.domain.season._team._mission_team.entity.TeamMission;
import com.github.kmu_shell_we.domain.season._team.entity.Team;
import com.github.kmu_shell_we.domain.season.entity.Season;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TeamMissionRepository extends JpaRepository<TeamMission, UUID> {

    List<TeamMission> findAllBySeasonAndTeamAndEndedAtBefore(Season season, Team team, LocalDateTime now);

    Optional<TeamMission> findBySeasonAndTeamAndMission(Season season, Team team, Mission mission);

    List<TeamMission> findAllBySeasonAndTeam(Season season, Team team);
}
