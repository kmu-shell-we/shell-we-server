package com.github.kmu_shell_we.domain.season._team._mission_team._submission_mission_team.service;

import com.github.kmu_shell_we.domain.mission.entity.Mission;
import com.github.kmu_shell_we.domain.mission.exception.MissionExceptions;
import com.github.kmu_shell_we.domain.mission.repository.MissionRepository;
import com.github.kmu_shell_we.domain.season._team._mission_team._submission_mission_team.dto.request.CreateSubmissionRequest;
import com.github.kmu_shell_we.domain.season._team._mission_team._submission_mission_team.dto.response.SubmissionListResponse;
import com.github.kmu_shell_we.domain.season._team._mission_team._submission_mission_team.dto.response.SubmissionResponse;
import com.github.kmu_shell_we.domain.season._team._mission_team._submission_mission_team.entity.Submission;
import com.github.kmu_shell_we.domain.season._team._mission_team._submission_mission_team.repository.SubmissionRepository;
import com.github.kmu_shell_we.domain.season._team._mission_team.entity.TeamMission;
import com.github.kmu_shell_we.domain.season._team._mission_team.exceptions.TeamMissionExceptions;
import com.github.kmu_shell_we.domain.season._team._mission_team.repository.TeamMissionRepository;
import com.github.kmu_shell_we.domain.season._team.entity.Team;
import com.github.kmu_shell_we.domain.season._team.exception.TeamExceptions;
import com.github.kmu_shell_we.domain.season._team.repository.TeamRepository;
import com.github.kmu_shell_we.domain.season.entity.Season;
import com.github.kmu_shell_we.domain.season.exception.SeasonExceptions;
import com.github.kmu_shell_we.domain.season.repository.SeasonRepository;
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
    private final SeasonRepository seasonRepository;
    private final TeamRepository teamRepository;
    private final MissionRepository missionRepository;

    @Transactional(readOnly = true)
    public SubmissionListResponse getSubmissions(UUID seasonId, UUID teamId, UUID missionId) {

        Season season = seasonRepository.findById(seasonId)
                .orElseThrow(SeasonExceptions.NOT_FOUND_SEASON::toException);
        Team team = teamRepository.findById(teamId)
                .orElseThrow(TeamExceptions.NOT_FOUND_TEAM::toException);
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(MissionExceptions.NOT_FOUND::toException);


        TeamMission teamMission = teamMissionRepository.findBySeasonAndTeamAndMission(season, team, mission)
                .orElseThrow(TeamMissionExceptions.NOT_FOUND_TEAM_MISSION::toException);

        List<Submission> submissions = submissionRepository.findAllByTeamMission(teamMission);

        return SubmissionListResponse.from(submissions);
    }

    @Transactional
    public SubmissionResponse submitSubmission(UUID seasonId, UUID teamId, UUID missionId, CreateSubmissionRequest request) {

        Season season = seasonRepository.findById(seasonId)
                .orElseThrow(SeasonExceptions.NOT_FOUND_SEASON::toException);
        Team team = teamRepository.findById(teamId)
                .orElseThrow(TeamExceptions.NOT_FOUND_TEAM::toException);
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(MissionExceptions.NOT_FOUND::toException);

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
