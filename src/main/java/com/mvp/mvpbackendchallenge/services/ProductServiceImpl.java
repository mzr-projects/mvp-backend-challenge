package com.mvp.mvpbackendchallenge.services;

import com.mvp.mvpbackendchallenge.entities.Product;
import com.mvp.mvpbackendchallenge.exceptions.ProductAlreadyExistsException;
import com.mvp.mvpbackendchallenge.exceptions.ProductDoesNotExistInStockException;
import com.mvp.mvpbackendchallenge.exceptions.ProductNotFoundException;
import com.mvp.mvpbackendchallenge.paloads.ProductDto;
import com.mvp.mvpbackendchallenge.repositories.ProductRepository;
import com.mvp.mvpbackendchallenge.security.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;

	@Override
	public ProductDto getProduct(String productName) {
		Product product = productRepository.findProductByProductName(productName);
		ProductDto productDto = new ProductDto();

		if (product != null) {
			productDto.setProductName(product.getProductName());
			productDto.setCost(product.getCost());
			productDto.setSellerId(product.getSellerId());
			productDto.setAmountAvailable(product.getAmountAvailable());
		} else {
			throw new ProductNotFoundException("Product not found.");
		}

		return productDto;
	}

	@Override
	public List<ProductDto> getAllProducts() {
		List<ProductDto> productDtoList = new ArrayList<>();
		List<Product> productList = productRepository.findAll();

		for (Product product : productList) {
			ProductDto productDto = new ProductDto();
			productDto.setProductName(product.getProductName());
			productDto.setAmountAvailable(product.getAmountAvailable());
			productDto.setCost(product.getCost());
			productDto.setSellerId(product.getSellerId());
			productDtoList.add(productDto);
		}

		return productDtoList;
	}

	@Override
	public void createProduct(ProductDto productDto) {
		Product product = productRepository.findProductByProductName(productDto.getProductName());
		if (product != null) {
			throw new ProductAlreadyExistsException("Product already exists.");
		} else {
			Product product1 = new Product();
			product1.setProductName(productDto.getProductName());
			product1.setCost(productDto.getCost());
			product1.setSellerId(productDto.getSellerId());
			product1.setAmountAvailable(productDto.getAmountAvailable());
			productRepository.save(product1);
		}
	}

	@Override
	public void editProduct(ProductDto productDto) {
		Product product = productRepository.findProductByProductName(productDto.getProductName());
		if (product != null) {
			product.setAmountAvailable(productDto.getAmountAvailable());
			product.setCost(productDto.getCost());
			product.setSellerId(productDto.getSellerId());
			product.setProductName(productDto.getProductName());
		} else {
			throw new ProductNotFoundException("Product not found.");
		}
	}

	@Override
	public void deleteProduct(String productName) {
		Product product = productRepository.findProductByProductName(productName);
		if (product != null)
			productRepository.deleteProductByProductName(productName);
		else
			throw new ProductNotFoundException("Product not found");
	}
}
