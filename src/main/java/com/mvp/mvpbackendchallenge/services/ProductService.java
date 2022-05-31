package com.mvp.mvpbackendchallenge.services;

import com.mvp.mvpbackendchallenge.paloads.ProductDto;

import java.util.List;

public interface ProductService {

	ProductDto getProduct(String productName);

	List<ProductDto> getAllProducts();

	void createProduct(ProductDto productDto);

	void editProduct(ProductDto productDto);

	void deleteProduct(String productId);
}
