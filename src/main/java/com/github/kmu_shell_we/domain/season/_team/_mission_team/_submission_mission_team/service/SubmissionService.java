package com.github.kmu_shell_we.domain.season._team._mission_team._submission_mission_team.service;

import com.github.kmu_shell_we.domain.mission.entity.Mission;
import com.github.kmu_shell_we.domain.season._team._mission_team._submission_mission_team.dto.request.CreateSubmissionRequest;
import com.github.kmu_shell_we.domain.season._team._mission_team._submission_mission_team.dto.response.SubmissionListResponse;
import com.github.kmu_shell_we.domain.season._team._mission_team._submission_mission_team.dto.response.SubmissionResponse;
import com.github.kmu_shell_we.domain.season._team._mission_team._submission_mission_team.entity.Submission;
import com.github.kmu_shell_we.domain.season._team._mission_team._submission_mission_team.repository.SubmissionRepository;
import com.github.kmu_shell_we.domain.season._team._mission_team.entity.TeamMission;
import com.github.kmu_shell_we.domain.season._team._mission_team.exceptions.TeamMissionExceptions;
import com.github.kmu_shell_we.domain.season._team._mission_team.repository.TeamMissionRepository;
import com.github.kmu_shell_we.domain.season._team.entity.Team;
import com.github.kmu_shell_we.domain.season.entity.Season;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubmissionService {

    private final TeamMissionRepository teamMissionRepository;
    private final SubmissionRepository submissionRepository;

    @Transactional(readOnly = true)
    public SubmissionListResponse getSubmissions(Season season, Team team) {

        List<TeamMission> teamMissions = teamMissionRepository.findAllBySeasonAndTeam(season, team);

        List<Submission> submissions = submissionRepository.findAllByTeamMissionIn(teamMissions);

        return SubmissionListResponse.from(submissions);
    }

    @Transactional(readOnly = true)
    public SubmissionResponse getSubmission(Season season, Team team, Mission mission) {

        TeamMission teamMission = teamMissionRepository.findBySeasonAndTeamAndMission(season, team, mission)
                .orElseThrow(TeamMissionExceptions.NOT_FOUND_TEAM_MISSION::toException);

        Submission submission = submissionRepository.findByTeamMission(teamMission);

        return SubmissionResponse.from(submission);
    }

    @Transactional
    public SubmissionResponse submitSubmission(Season season, Team team, Mission mission, CreateSubmissionRequest request) {

        TeamMission teamMission = teamMissionRepository.findBySeasonAndTeamAndMission(season, team, mission)
                .orElseThrow(TeamMissionExceptions.NOT_FOUND_TEAM_MISSION::toException);

        Submission submission = submissionRepository.save(Submission
                .builder()
                .teamMission(teamMission)
                .image(request.getImage())
                .build()
        );

        return SubmissionResponse.from(submission);
    }
}
