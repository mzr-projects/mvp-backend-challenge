package com.mvp.mvpbackendchallenge.validators;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ProductCostValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ProductCostConstraint {

	String message() default "Invalid cost value (must be multiple of 5)";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
