package com.pisoft.pisoft.annotion;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class RoleValidator implements ConstraintValidator<RoleValidation, String> {

    @Override
    public boolean isValid(String inputValue, ConstraintValidatorContext context) {

        System.out.println("input : "+inputValue);

        List<String> list = List.of("ADMIN", "USER");
        return list.contains(inputValue);
    }
}
