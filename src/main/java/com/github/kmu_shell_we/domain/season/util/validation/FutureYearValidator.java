package com.github.kmu_shell_we.domain.season.util.validation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class FutureYearValidator implements ConstraintValidator<FutureYear, Integer> {

    @Override
    public boolean isValid(Integer year, ConstraintValidatorContext context) {

        if (year == null) {

            return true;
        }

        return year >= LocalDate.now().getYear();
    }
}
