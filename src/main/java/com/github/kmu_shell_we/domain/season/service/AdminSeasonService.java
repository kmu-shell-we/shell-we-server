package com.github.kmu_shell_we.domain.season.service;

import com.github.kmu_shell_we.domain.season.dto.request.CreateSeasonRequest;
import com.github.kmu_shell_we.domain.season.dto.request.UpdateSeasonRequest;
import com.github.kmu_shell_we.domain.season.dto.response.SeasonListResponse;
import com.github.kmu_shell_we.domain.season.dto.response.SeasonResponse;
import com.github.kmu_shell_we.domain.season.entity.Season;
import com.github.kmu_shell_we.domain.season.exception.SeasonExceptionCode;
import com.github.kmu_shell_we.domain.season.repository.SeasonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminSeasonService {

    private final SeasonRepository seasonRepository;

    @Transactional(readOnly = true)
    public SeasonListResponse getSeasons() {

        List<Season> seasons = seasonRepository.findAll();

        return SeasonListResponse.from(seasons);
    }

    @Transactional(readOnly = true)
    public SeasonResponse getSeason(UUID seasonId) {

        Season season = seasonRepository.findById(seasonId)
                .orElseThrow(SeasonExceptionCode.NOT_FOUND::toException);

        return SeasonResponse.from(season);
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
    public SeasonResponse updateSeason(UUID seasonId, UpdateSeasonRequest request) {

        Season season = seasonRepository.findById(seasonId)
                .orElseThrow(SeasonExceptionCode.NOT_FOUND::toException)
                .toBuilder()
                .startedAt(request.getStartedAt())
                .endedAt(request.getEndedAt())
                .build();

        return SeasonResponse.from(season);
    }

    @Transactional
    public void deleteSeason(UUID seasonId) {

        Season season = seasonRepository.findById(seasonId)
                .orElseThrow(SeasonExceptionCode.NOT_FOUND::toException);

        seasonRepository.delete(season);
    }
}
