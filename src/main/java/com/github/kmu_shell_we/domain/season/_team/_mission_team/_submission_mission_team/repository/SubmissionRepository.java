package com.github.kmu_shell_we.domain.season._team._mission_team._submission_mission_team.repository;

import com.github.kmu_shell_we.domain.season._team._mission_team._submission_mission_team.entity.Submission;
import com.github.kmu_shell_we.domain.season._team._mission_team.entity.TeamMission;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SubmissionRepository extends JpaRepository<Submission, UUID> {

    List<Submission> findAllByTeamMission(TeamMission teamMission);
}
