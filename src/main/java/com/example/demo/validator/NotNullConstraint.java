package com.example.demo.validator;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by dhf_ndm on 2019/2/21
 * the previous bug derived from the previous
 */
public class NotNullConstraint implements ConstraintValidator<NotNull,String> {

    @Autowired
    Gson gson;

    private String[] value;

    @Override
    public void initialize(NotNull constraintAnnotation) {
        this.value = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String o, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println("验证"+o);
        return !StringUtils.isEmpty(o);
    }
}
