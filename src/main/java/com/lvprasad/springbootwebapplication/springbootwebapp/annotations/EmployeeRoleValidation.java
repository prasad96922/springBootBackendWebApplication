package com.lvprasad.springbootwebapplication.springbootwebapp.annotations;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target({ElementType.FIELD,  ElementType.PARAMETER})
@Constraint(validatedBy = { EmployeeRoleValidator.class})
public @interface EmployeeRoleValidation {
    String message() default "Role of Employee can either be USER or ADMIN";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
