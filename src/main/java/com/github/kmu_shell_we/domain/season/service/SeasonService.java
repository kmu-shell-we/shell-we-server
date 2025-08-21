package com.github.kmu_shell_we.domain.season.service;

import com.github.kmu_shell_we.domain.season._team.dto.response.SimpleTeamResponse;
import com.github.kmu_shell_we.domain.season._team.entity.Team;
import com.github.kmu_shell_we.domain.season._team.repository.TeamRepository;
import com.github.kmu_shell_we.domain.season.dto.response.SeasonResponse;
import com.github.kmu_shell_we.domain.season.entity.Season;
import com.github.kmu_shell_we.domain.season.repository.SeasonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeasonService {

    private final SeasonRepository seasonRepository;
    private final TeamRepository teamRepository;

    private static final int TOP_TEAM_LIMIT = 3;

    @Transactional(readOnly = true)
    public SeasonResponse getCurrentSeason() {

        return seasonRepository.findCurrentSeason()
                .map(SeasonResponse::from)
                .orElseGet(SeasonResponse::empty);
    }

    @Transactional(readOnly = true)
    @PreAuthorize("#season.isCurrentSeason()")
    public List<SimpleTeamResponse> getCurrentSeasonRanks(Season season, boolean simplified) {

        List<Team> teams = teamRepository.findAllBySeasonOrderByPointDesc(season);

        if (simplified && teams.size() > TOP_TEAM_LIMIT) {
            teams = teams.subList(0, TOP_TEAM_LIMIT);
        }

        return teams.stream()
                .map(SimpleTeamResponse::from)
                .toList();
    }
}
