package com.github.kmu_shell_we.domain.season.service;

import com.github.kmu_shell_we.domain.season.dto.response.SeasonResponse;
import com.github.kmu_shell_we.domain.season.entity.Season;
import com.github.kmu_shell_we.domain.season.repository.SeasonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SeasonService {

    private final SeasonRepository seasonRepository;

    @Transactional(readOnly = true)
    public SeasonResponse getCurrentSeason() {

        Season season = seasonRepository.findCurrentSeason();

        return Objects.isNull(season) ? SeasonResponse.empty() : SeasonResponse.from(season);
    }
}
