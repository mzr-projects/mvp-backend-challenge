package com.mvp.mvpbackendchallenge.paloads;

import lombok.Data;

@Data
public class BuyProductDto {
	private int spentMoney;
	private String purchasedProduct;
	private int changes;
}
