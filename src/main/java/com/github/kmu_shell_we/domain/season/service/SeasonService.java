package com.github.kmu_shell_we.domain.season.service;

import com.github.kmu_shell_we.domain.season.dto.request.CreateSeasonRequest;
import com.github.kmu_shell_we.domain.season.dto.response.SeasonResponse;
import com.github.kmu_shell_we.domain.season.entity.Season;
import com.github.kmu_shell_we.domain.season.repository.SeasonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeasonService {

    private final SeasonRepository seasonRepository;

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
}
