package com.github.kmu_shell_we.domain.season.util.validation;

import com.github.kmu_shell_we.domain.season.dto.request.CreateSeasonRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CreateStartBeforeEndDateValidator implements ConstraintValidator<CreateStartBeforeEndDate, CreateSeasonRequest> {

    @Override
    public boolean isValid(CreateSeasonRequest request, ConstraintValidatorContext context) {

        return request.getStartedAt().isBefore(request.getEndedAt());
    }
}
