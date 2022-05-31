package com.mvp.mvpbackendchallenge.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProductCostValidator implements ConstraintValidator<ProductCostConstraint, Integer> {

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {

		boolean isValid = false;

		if (value == null) {
			return false;
		}

		if (value % 5 == 0) {
			isValid = true;
		}

		return isValid;
	}
}
