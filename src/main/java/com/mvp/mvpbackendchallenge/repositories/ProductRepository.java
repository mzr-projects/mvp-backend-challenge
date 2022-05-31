package com.mvp.mvpbackendchallenge.repositories;

import com.mvp.mvpbackendchallenge.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

	Product findProductByProductName(String productName);

	Product deleteProductByProductName(String productName);
}
