package com.mvp.mvpbackendchallenge.paloads;

import com.mvp.mvpbackendchallenge.validators.ProductCostConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

	private String productName;

	private Long amountAvailable;

	@ProductCostConstraint
	private int cost;

	private Long sellerId;
}
