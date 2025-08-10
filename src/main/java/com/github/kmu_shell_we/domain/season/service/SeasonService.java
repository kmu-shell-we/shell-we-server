package com.github.kmu_shell_we.domain.season.service;

import com.github.kmu_shell_we.domain.season.dto.response.SeasonResponse;
import com.github.kmu_shell_we.domain.season.repository.SeasonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SeasonService {

    private final SeasonRepository seasonRepository;

    @Transactional(readOnly = true)
    public SeasonResponse getCurrentSeason() {

        return seasonRepository.findCurrentSeason()
                .map(SeasonResponse::from)
                .orElseGet(SeasonResponse::empty);
    }
}
