package com.github.kmu_shell_we.domain.season.dto.request;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class CreateSeasonRequest {

    @NotBlank
    @Max(2099)
    Integer year;

    @NotBlank
    @Min(1)
    @Max(2)
    Integer semester;

    @NotBlank
    LocalDateTime startedAt;

    @NotBlank
    LocalDateTime endedAt;

    @AssertTrue(message = "현재 연도 이후의 시즌만 생성할 수 있어야 한다.")
    public boolean isValidYear() {

        return this.year >= LocalDate.now().getYear();
    }

    @AssertTrue(message = "시작일은 종료일보다 앞서야 한다.")
    public boolean isValidDateTime() {

        return this.startedAt.isBefore(this.endedAt);
    }
}
