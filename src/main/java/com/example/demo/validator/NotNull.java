package com.example.demo.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by dhf_ndm on 2019/2/21
 * the previous bug derived from the previous
 */
@Documented
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotNullConstraint.class)
public @interface NotNull {

    String[] value() default {};
    String message() default "参数异常";
    Class<?> [] groups()default {};
    Class<? extends Payload>[] payload() default {};
}
