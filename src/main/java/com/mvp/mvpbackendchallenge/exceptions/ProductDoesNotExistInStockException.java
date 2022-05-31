package com.mvp.mvpbackendchallenge.exceptions;

public class ProductDoesNotExistInStockException extends RuntimeException {

	public ProductDoesNotExistInStockException(String message) {
		super(message);
	}
}
