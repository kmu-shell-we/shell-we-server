package com.github.kmu_shell_we.domain.season.util.validation;

import com.github.kmu_shell_we.domain.season.dto.request.UpdateSeasonRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UpdateStartBeforeEndDateValidator implements ConstraintValidator<CreateStartBeforeEndDate, UpdateSeasonRequest> {

    @Override
    public boolean isValid(UpdateSeasonRequest request, ConstraintValidatorContext context) {

        return request.getStartedAt().isBefore(request.getEndedAt());
    }
}
