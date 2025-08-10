package com.github.kmu_shell_we.domain.season.service;

import com.github.kmu_shell_we.domain.season.dto.request.CreateSeasonRequest;
import com.github.kmu_shell_we.domain.season.dto.request.UpdateSeasonRequest;
import com.github.kmu_shell_we.domain.season.dto.response.SeasonListResponse;
import com.github.kmu_shell_we.domain.season.dto.response.SeasonResponse;
import com.github.kmu_shell_we.domain.season.entity.Season;
import com.github.kmu_shell_we.domain.season.repository.SeasonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminSeasonService {

    private final SeasonRepository seasonRepository;

    @Transactional(readOnly = true)
    public SeasonListResponse getSeasons() {

        return SeasonListResponse.from(seasonRepository.findAll());
    }

    @Transactional
    public SeasonResponse createSeason(CreateSeasonRequest request) {

        Season season = seasonRepository.save(
                Season.builder()
                        .year(request.getYear())
                        .semester(request.getSemester())
                        .startedAt(request.getStartedAt())
                        .endedAt(request.getEndedAt())
                        .build()
        );

        return SeasonResponse.from(season);
    }

    @Transactional
    public SeasonResponse updateSeason(Season season, UpdateSeasonRequest request) {

        season = seasonRepository.save(
                season.toBuilder()
                        .startedAt(request.getStartedAt())
                        .endedAt(request.getEndedAt())
                        .build()
        );

        return SeasonResponse.from(season);
    }

    @Transactional
    public void deleteSeason(Season season) {

        seasonRepository.delete(season);
    }
}
