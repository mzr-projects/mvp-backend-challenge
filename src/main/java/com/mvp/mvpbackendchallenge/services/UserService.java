package com.mvp.mvpbackendchallenge.services;

import com.mvp.mvpbackendchallenge.paloads.CreateUserDto;
import com.mvp.mvpbackendchallenge.paloads.DepositDto;

public interface UserService {

	void createUser(CreateUserDto createUserDto);
	void deleteUser(String username);
	void updateUser(CreateUserDto createUserDto);
	void deposit(DepositDto depositDto);

	void resetUser();
}
