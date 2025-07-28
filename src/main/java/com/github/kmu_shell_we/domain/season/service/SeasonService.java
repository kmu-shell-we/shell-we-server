package com.github.kmu_shell_we.domain.season.service;

import com.github.kmu_shell_we.domain.season.dto.request.CreateSeasonRequest;
import com.github.kmu_shell_we.domain.season.dto.request.UpdateSeasonRequest;
import com.github.kmu_shell_we.domain.season.dto.response.SeasonListResponse;
import com.github.kmu_shell_we.domain.season.dto.response.SeasonResponse;
import com.github.kmu_shell_we.domain.season.entity.Season;
import com.github.kmu_shell_we.domain.season.exception.SeasonExceptionCode;
import com.github.kmu_shell_we.domain.season.repository.AdminSeasonRepository;
import com.github.kmu_shell_we.domain.season.repository.SeasonRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SeasonService {

    private final AdminSeasonRepository adminSeasonRepository;
    private final SeasonRepository seasonRepository;

    @Transactional
    public SeasonListResponse getSeasons() {

        List<Season> seasons = adminSeasonRepository.findAll();

        return SeasonListResponse.from(seasons);
    }

    @Transactional
    public SeasonResponse getSeason(UUID seasonId) {

        Season season = adminSeasonRepository.findById(seasonId).orElseThrow(SeasonExceptionCode.NOT_FOUND_SEASON::toException);

        return SeasonResponse.from(season);
    }

    @Transactional
    public SeasonResponse getCurrentSeason() {

        Season season = seasonRepository.findCurrent(LocalDateTime.now())
                .orElseThrow(SeasonExceptionCode.NOT_FOUND_CURRENT_SEASON::toException);

        return  SeasonResponse.from(season);
    }

    @Transactional
    public SeasonResponse createSeason(CreateSeasonRequest request) {

        Season season = adminSeasonRepository.save(
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

        Season season = adminSeasonRepository.findById(seasonId)
                .orElseThrow(SeasonExceptionCode.NOT_FOUND_SEASON::toException)
                .toBuilder()
                .startedAt(request.getStartedAt())
                .endedAt(request.getEndedAt())
                .build();

        seasonRepository.save(season);

        return SeasonResponse.from(season);
    }

    @Transactional
    public void deleteSeason(UUID seasonId) {

        Season season = adminSeasonRepository.findById(seasonId)
                .orElseThrow(SeasonExceptionCode.NOT_FOUND_SEASON::toException);

        seasonRepository.deleteById(season.getId());
    }
}
