package com.mvp.mvpbackendchallenge.paloads;

import com.mvp.mvpbackendchallenge.validators.CoinAmountConstraint;
import lombok.Data;

@Data
public class DepositDto {

	private String userName;

	@CoinAmountConstraint
	private int deposit;
}
