package com.mvp.mvpbackendchallenge.controllers;

import com.mvp.mvpbackendchallenge.paloads.BuyDto;
import com.mvp.mvpbackendchallenge.paloads.BuyProductDto;
import com.mvp.mvpbackendchallenge.services.UserProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/mvp/vending/user-products")
@RequiredArgsConstructor
public class UserProductController {

	private final UserProductService userProductService;

	@PreAuthorize("hasRole('ROLE_BUYER')")
	@PostMapping("/buy")
	public ResponseEntity<BuyProductDto> buyProduct(@RequestBody BuyDto buyDto) {
		BuyProductDto buyProductDto = userProductService.buyProduct(buyDto);
		return new ResponseEntity<>(buyProductDto, HttpStatus.OK);
	}
}
