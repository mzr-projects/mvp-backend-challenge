package com.mvp.mvpbackendchallenge.validators;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CoinAmountValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CoinAmountConstraint {

	String message() default "Invalid Coin number";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
