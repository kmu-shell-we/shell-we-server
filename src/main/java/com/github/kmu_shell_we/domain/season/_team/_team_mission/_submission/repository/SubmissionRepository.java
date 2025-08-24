package com.github.kmu_shell_we.domain.season._team._team_mission._submission.repository;

import com.github.kmu_shell_we.domain.season._team._team_mission._submission.entity.Submission;
import com.github.kmu_shell_we.domain.season._team._team_mission.entity.TeamMission;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SubmissionRepository extends JpaRepository<Submission, UUID> {

    Submission findByTeamMission(TeamMission teamMission);

    List<Submission> findAllByTeamMissionIn(List<TeamMission> teamMissions);
}
