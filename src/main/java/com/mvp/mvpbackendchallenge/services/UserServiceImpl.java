package com.mvp.mvpbackendchallenge.services;

import com.mvp.mvpbackendchallenge.entities.VendingMachineUser;
import com.mvp.mvpbackendchallenge.entities.VendingMachineUserRole;
import com.mvp.mvpbackendchallenge.exceptions.UserAlreadyExistException;
import com.mvp.mvpbackendchallenge.paloads.CreateUserDto;
import com.mvp.mvpbackendchallenge.paloads.DepositDto;
import com.mvp.mvpbackendchallenge.repositories.UserRepository;
import com.mvp.mvpbackendchallenge.repositories.UserRoleRepository;
import com.mvp.mvpbackendchallenge.security.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final UserRoleRepository userRoleRepository;
	private final PasswordEncoder passwordEncoder;

	private final AuthenticationFacade authenticationFacade;

	@Override
	public void createUser(CreateUserDto createUserDto) {
		VendingMachineUser vendingMachineUser = userRepository.findVendingMachineUserByUserName(createUserDto.getUserName());

		if (vendingMachineUser != null) {
			throw new UserAlreadyExistException("User already exists.");
		}

		vendingMachineUser = new VendingMachineUser();
		vendingMachineUser.setUserName(createUserDto.getUserName());
		vendingMachineUser.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
		vendingMachineUser.setDeposit(0);

		VendingMachineUserRole vendingMachineUserRole = userRoleRepository.findVendingMachineUserRoleByRoleName("BUYER");
		Set<VendingMachineUserRole> vendingMachineUserRoleSet = new HashSet<>();
		vendingMachineUserRoleSet.add(vendingMachineUserRole);
		vendingMachineUser.setVendingMachineUserRoleSet(vendingMachineUserRoleSet);

		userRepository.save(vendingMachineUser);
	}

	@Override
	public void updateUser(CreateUserDto createUserDto) {
		VendingMachineUser vendingMachineUser = userRepository.findVendingMachineUserByUserName(createUserDto.getUserName());

		if (vendingMachineUser == null) {
			throw new UsernameNotFoundException("User not found");
		}

		vendingMachineUser.setUserName(createUserDto.getUserName());
		vendingMachineUser.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
		userRepository.save(vendingMachineUser);
	}

	@Override
	public void deleteUser(String userName) {
		VendingMachineUser vendingMachineUser = userRepository.findVendingMachineUserByUserName(userName);
		if (vendingMachineUser == null) {
			throw new UsernameNotFoundException("User does not exist");
		}
		userRepository.deleteVendingMachineUserByUserName(userName);
	}

	@Override
	public void deposit(DepositDto depositDto) {
		VendingMachineUser vendingMachineUser = userRepository.findVendingMachineUserByUserName(depositDto.getUserName());

		if (vendingMachineUser == null) {
			throw new UsernameNotFoundException("User not found");
		}

		vendingMachineUser.setDeposit(depositDto.getDeposit() + vendingMachineUser.getDeposit());
		userRepository.save(vendingMachineUser);
	}

	@Override
	public void resetUser() {
		VendingMachineUser vendingMachineUser = userRepository
				.findVendingMachineUserByUserName(authenticationFacade.getAuthentication().getName());
		vendingMachineUser.setDeposit(0);
	}

}
