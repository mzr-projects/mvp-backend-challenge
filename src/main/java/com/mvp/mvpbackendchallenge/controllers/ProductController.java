package com.mvp.mvpbackendchallenge.controllers;

import com.mvp.mvpbackendchallenge.paloads.ProductDto;
import com.mvp.mvpbackendchallenge.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/mvp/vending/products")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;

	@GetMapping("/get/all")
	public ResponseEntity<List<ProductDto>> getAllProducts() {
		return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
	}

	@GetMapping("/get/{productName}")
	public ResponseEntity<ProductDto> getProduct(@Valid @PathVariable String productName) {
		return new ResponseEntity<>(productService.getProduct(productName), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_SELLER')")
	@PostMapping("/add")
	public ResponseEntity<String> addProduct(@Valid @RequestBody ProductDto productDto) {
		productService.createProduct(productDto);
		return new ResponseEntity<>("Product added successfully", HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ROLE_SELLER')")
	@PutMapping("/edit")
	public ResponseEntity<String> editProduct(@Valid @RequestBody ProductDto productDto) {
		productService.editProduct(productDto);
		return new ResponseEntity<>("Product edited successfully", HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_SELLER')")
	@PostMapping("/delete/{productName}")
	public ResponseEntity<String> deleteProduct(@PathVariable String productName) {
		productService.deleteProduct(productName);
		return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
	}
}
