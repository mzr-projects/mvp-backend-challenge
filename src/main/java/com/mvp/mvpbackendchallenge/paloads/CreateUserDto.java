package com.mvp.mvpbackendchallenge.paloads;

import com.mvp.mvpbackendchallenge.validators.CoinAmountConstraint;
import lombok.Data;

@Data
public class CreateUserDto {

	private String userName;
	private String password;

	@CoinAmountConstraint
	private int deposit;
}
