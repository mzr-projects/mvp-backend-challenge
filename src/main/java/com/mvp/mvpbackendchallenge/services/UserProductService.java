package com.mvp.mvpbackendchallenge.services;

import com.mvp.mvpbackendchallenge.paloads.BuyDto;
import com.mvp.mvpbackendchallenge.paloads.BuyProductDto;

public interface UserProductService {
	BuyProductDto buyProduct(BuyDto buyDto);
}
