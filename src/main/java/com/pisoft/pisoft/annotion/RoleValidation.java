package com.pisoft.pisoft.annotion;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD , ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = RoleValidator.class)
public @interface RoleValidation {

    String message() default "Role should be either ADMIN OR USER";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
