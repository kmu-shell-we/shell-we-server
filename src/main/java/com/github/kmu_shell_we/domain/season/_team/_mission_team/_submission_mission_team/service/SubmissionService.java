package com.github.kmu_shell_we.domain.season._team._mission_team._submission_mission_team.service;

import com.github.kmu_shell_we.domain.season._team._mission_team._submission_mission_team.dto.request.CreateSubmissionRequest;
import com.github.kmu_shell_we.domain.season._team._mission_team._submission_mission_team.dto.response.SubmissionListResponse;
import com.github.kmu_shell_we.domain.season._team._mission_team._submission_mission_team.dto.response.SubmissionResponse;
import com.github.kmu_shell_we.domain.season._team._mission_team._submission_mission_team.entity.Submission;
import com.github.kmu_shell_we.domain.season._team._mission_team._submission_mission_team.repository.SubmissionRepository;
import com.github.kmu_shell_we.domain.season._team._mission_team.entity.TeamMission;
import com.github.kmu_shell_we.domain.season._team._mission_team.exceptions.TeamMissionExceptions;
import com.github.kmu_shell_we.domain.season._team._mission_team.repository.TeamMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubmissionService {

    private final TeamMissionRepository teamMissionRepository;
    private final SubmissionRepository submissionRepository;

    @Transactional(readOnly = true)
    public SubmissionListResponse getSubmissions(UUID seasonId, UUID teamId, UUID missionId) {

        TeamMission teamMission = teamMissionRepository.findBySeasonIdAndTeamIdAndMissionId(seasonId, teamId, missionId)
                .orElseThrow(TeamMissionExceptions.NOT_FOUND_TEAM_MISSION::toException);

        List<Submission> submissions = submissionRepository.findAllByTeamMission(teamMission);

        return SubmissionListResponse.from(submissions);
    }

    @Transactional
    public SubmissionResponse submitSubmission(UUID seasonId, UUID teamId, UUID missionId, CreateSubmissionRequest request) {

        TeamMission teamMission = teamMissionRepository.findBySeasonIdAndTeamIdAndMissionId(seasonId, teamId, missionId)
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
