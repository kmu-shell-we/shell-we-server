package com.github.kmu_shell_we.domain.season.util.validation;


import com.github.kmu_shell_we.domain.season.dto.request.CreateSeasonRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class FutureYearValidator implements ConstraintValidator<FutureYear, CreateSeasonRequest> {

    @Override
    public boolean isValid(CreateSeasonRequest request, ConstraintValidatorContext context) {
        return request.getYear() >= LocalDate.now().getYear();
    }
}
