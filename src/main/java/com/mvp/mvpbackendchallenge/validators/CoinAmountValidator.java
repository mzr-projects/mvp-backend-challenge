package com.mvp.mvpbackendchallenge.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CoinAmountValidator implements ConstraintValidator<CoinAmountConstraint, Integer> {

	private final int[] acceptableCoins = new int[]{5, 10, 20, 50, 100};

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {

		boolean isValid = false;

		if (value == null) {
			return false;
		}

		for (int acceptableCoin : acceptableCoins) {
			if (value == acceptableCoin) {
				isValid = true;
				break;
			}
		}

		return isValid;
	}
}
