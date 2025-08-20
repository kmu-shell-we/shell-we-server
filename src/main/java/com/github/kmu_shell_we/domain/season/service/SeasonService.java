package com.github.kmu_shell_we.domain.season.service;

import com.github.kmu_shell_we.domain.season._team.dto.response.SimpleTeamResponse;
import com.github.kmu_shell_we.domain.season._team.entity.Team;
import com.github.kmu_shell_we.domain.season._team.repository.TeamRepository;
import com.github.kmu_shell_we.domain.season.dto.response.SeasonResponse;
import com.github.kmu_shell_we.domain.season.entity.Season;
import com.github.kmu_shell_we.domain.season.repository.SeasonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SeasonService {

    private final SeasonRepository seasonRepository;
    private final TeamRepository teamRepository;

    @Transactional(readOnly = true)
    public SeasonResponse getCurrentSeason() {

        return seasonRepository.findCurrentSeason()
                .map(SeasonResponse::from)
                .orElseGet(SeasonResponse::empty);
    }

    @Transactional(readOnly = true)
    public List<SimpleTeamResponse> getCurrentSeasonRanks(Season season, boolean simplified) {

        List<Team> teams = teamRepository.findAllBySeasonOrderByPointDesc(season);

        if (simplified && teams.size() > 3) {
            teams = teams.subList(0, 3);
        }

        return teams.stream()
                .map(SimpleTeamResponse::from)
                .collect(Collectors.toList());
    }
}
