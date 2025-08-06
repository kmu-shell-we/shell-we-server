package com.github.kmu_shell_we.domain.season._team._mission_team.repository;

import com.github.kmu_shell_we.domain.season._team._mission_team.entity.TeamMission;
import com.github.kmu_shell_we.domain.season._team.entity.Team;
import com.github.kmu_shell_we.domain.season.entity.Season;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TeamMissionRepository extends JpaRepository<TeamMission, UUID> {

    Optional<TeamMission> findBySeasonIdAndTeamIdAndMissionId(UUID seasonId, UUID teamId, UUID missionId);

    List<TeamMission> findAllBySeasonAndTeam(Season season, Team team);
}
