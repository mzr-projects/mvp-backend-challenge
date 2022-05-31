package com.mvp.mvpbackendchallenge.controllers;

import com.mvp.mvpbackendchallenge.paloads.CreateUserDto;
import com.mvp.mvpbackendchallenge.paloads.DepositDto;
import com.mvp.mvpbackendchallenge.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/mvp/vending/user")
@RequiredArgsConstructor
public class VendingMachineUserController {

	private final UserService userService;

	@PostMapping("/register")
	public ResponseEntity<String> createUser(@Valid @RequestBody CreateUserDto createUserDto) {
		userService.createUser(createUserDto);
		return new ResponseEntity<>("User created successfully.", HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ROLE_SELLER')")
	@PutMapping("/update")
	public ResponseEntity<String> updateUser(@Valid @RequestBody CreateUserDto createUserDto) {
		userService.updateUser(createUserDto);
		return new ResponseEntity<>("User created successfully.", HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_SELLER')")
	@DeleteMapping("/delete/{userName}")
	public ResponseEntity<String> deleteUser(@PathVariable String userName) {
		userService.deleteUser(userName);
		return new ResponseEntity<>("User deleted successfully.", HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_BUYER')")
	@PostMapping("/deposit")
	public ResponseEntity<String> deposit(@Valid @RequestBody DepositDto depositDto) {
		userService.deposit(depositDto);
		return new ResponseEntity<>("User deposited some coin into the account successfully.", HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_BUYER')")
	@GetMapping("/reset")
	public ResponseEntity<String> resetUser() {
		userService.resetUser();
		return new ResponseEntity<>("User deposited reset to zero.", HttpStatus.OK);
	}
}
