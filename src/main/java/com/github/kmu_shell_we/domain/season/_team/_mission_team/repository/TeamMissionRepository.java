package com.github.kmu_shell_we.domain.season._team._mission_team.repository;

import com.github.kmu_shell_we.domain.mission.entity.Mission;
import com.github.kmu_shell_we.domain.season._team._mission_team.entity.TeamMission;
import com.github.kmu_shell_we.domain.season._team.entity.Team;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TeamMissionRepository extends JpaRepository<TeamMission, UUID> {

    @EntityGraph(attributePaths = {"team", "mission"})
    Optional<TeamMission> findByTeamAndMission(Team team, Mission mission);

    List<TeamMission> findAllByTeamAndEndedAtBefore(Team team, LocalDateTime now);
}
