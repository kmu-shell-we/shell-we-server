package com.github.kmu_shell_we.domain.season._team._team_mission._submission.service;

import com.github.kmu_shell_we.domain.season._team._team_mission._submission.dto.request.CreateSubmissionRequest;
import com.github.kmu_shell_we.domain.season._team._team_mission._submission.dto.response.SubmissionListResponse;
import com.github.kmu_shell_we.domain.season._team._team_mission._submission.dto.response.SubmissionResponse;
import com.github.kmu_shell_we.domain.season._team._team_mission._submission.entity.Submission;
import com.github.kmu_shell_we.domain.season._team._team_mission._submission.repository.SubmissionRepository;
import com.github.kmu_shell_we.domain.season._team._team_mission.entity.TeamMission;
import com.github.kmu_shell_we.domain.season._team._team_mission.repository.TeamMissionRepository;
import com.github.kmu_shell_we.domain.season._team._user_team.repository.UserTeamRepository;
import com.github.kmu_shell_we.domain.season._team.entity.Team;
import com.github.kmu_shell_we.domain.season.entity.Season;
import com.github.kmu_shell_we.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubmissionService {

    private final UserTeamRepository userTeamRepository;
    private final TeamMissionRepository teamMissionRepository;
    private final SubmissionRepository submissionRepository;

    @Transactional(readOnly = true)
    @PreAuthorize("@submissionService.canAccessSubmission(#user, #season, #team)")
    public SubmissionListResponse getSubmissions(User user, Season season, Team team) {

        List<TeamMission> teamMissions = teamMissionRepository.findAllByTeam(team);

        List<Submission> submissions = submissionRepository.findAllByTeamMissionIn(teamMissions);

        return SubmissionListResponse.from(submissions);
    }

    @Transactional(readOnly = true)
    @PreAuthorize("@submissionService.canAccessSubmission(#user, #season, #team)")
    public SubmissionResponse getSubmission(User user, Season season, Team team, TeamMission teamMission) {

        Submission submission = submissionRepository.findByTeamMission(teamMission);

        return SubmissionResponse.from(submission);
    }

    @Transactional
    @PreAuthorize("@submissionService.canAccessSubmission(#user, #season, #team)")
    public SubmissionResponse submitSubmission(User user, Season season, Team team, TeamMission teamMission, CreateSubmissionRequest request) {

        Submission submission = submissionRepository.save(Submission
                .builder()
                .teamMission(teamMission)
                .image(request.getImage())
                .build()
        );

        return SubmissionResponse.from(submission);
    }

    public boolean canAccessSubmission(User user, Season season, Team team) {

        return userTeamRepository.findByUserAndTeam(user, team).isPresent()
                && season.isCurrentSeason()
                && season.equals(team.getSeason());
    }
}
