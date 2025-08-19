package com.github.kmu_shell_we.domain.season._team._mission_team.repository;

import com.github.kmu_shell_we.domain.mission.entity.Mission;
import com.github.kmu_shell_we.domain.season._team._mission_team.entity.TeamMission;
import com.github.kmu_shell_we.domain.season._team.entity.Team;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TeamMissionRepository extends JpaRepository<TeamMission, UUID> {

    @EntityGraph(attributePaths = {"team", "mission"})
    Optional<TeamMission> findByTeamAndMission(Team team, Mission mission);

    List<TeamMission> findAllByTeamAndEndedAtGreaterThanEqual(Team team, LocalDateTime now);

    List<TeamMission> findAllByTeam(Team team);

    @Query("SELECT tm FROM TeamMission tm WHERE tm.team = :team and tm.endedAt >= :now and tm.submission is null and tm.mission.type = 'DAILY' ORDER BY RAND() LIMIT 1")
    Optional<TeamMission> findCurrentMission(Team team, LocalDateTime now);
}
