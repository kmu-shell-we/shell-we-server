package com.github.kmu_shell_we.domain.season.exception;

import com.github.kmu_shell_we.global.exception.ApiException;

public class NotFoundSeason extends ApiException {

    public NotFoundSeason() {

        super("SEASON_001", "시즌을 찾을 수 없습니다.");
    }
}
