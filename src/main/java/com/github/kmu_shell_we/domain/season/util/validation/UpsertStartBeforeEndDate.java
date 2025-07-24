package com.github.kmu_shell_we.domain.season.util.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UpsertStartBeforeEndDateValidator.class)
@Target({ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface UpsertStartBeforeEndDate {

    String message() default "시작일은 종료일보다 앞서야 한다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
