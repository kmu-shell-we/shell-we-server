package com.github.kmu_shell_we.domain.season.util.validation;

import com.github.kmu_shell_we.domain.season.dto.request.UpsertSeasonRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UpsertStartBeforeEndDateValidator implements ConstraintValidator<UpsertStartBeforeEndDate, UpsertSeasonRequest> {

    @Override
    public boolean isValid(UpsertSeasonRequest request, ConstraintValidatorContext context) {

        return request.getStartedAt().isBefore(request.getEndedAt());
    }
}
