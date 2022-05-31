package com.mvp.mvpbackendchallenge.services;

import com.mvp.mvpbackendchallenge.entities.Product;
import com.mvp.mvpbackendchallenge.entities.VendingMachineUser;
import com.mvp.mvpbackendchallenge.exceptions.NotEnoughMoneyException;
import com.mvp.mvpbackendchallenge.exceptions.ProductDoesNotExistInStockException;
import com.mvp.mvpbackendchallenge.exceptions.ProductNotFoundException;
import com.mvp.mvpbackendchallenge.paloads.BuyDto;
import com.mvp.mvpbackendchallenge.paloads.BuyProductDto;
import com.mvp.mvpbackendchallenge.repositories.ProductRepository;
import com.mvp.mvpbackendchallenge.repositories.UserRepository;
import com.mvp.mvpbackendchallenge.security.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserProductServiceImpl implements UserProductService {

	private final ProductRepository productRepository;
	private final UserRepository userRepository;
	private final AuthenticationFacade authenticationFacade;

	@Override
	public BuyProductDto buyProduct(BuyDto buyDto) {
		Product product = productRepository.findProductByProductName(buyDto.getProductName());

		if (product == null) {
			throw new ProductNotFoundException("Product does not exist.");
		}

		if (product.getAmountAvailable() == 0L) {
			throw new ProductDoesNotExistInStockException("Product out of stuck.");
		}

		if (product.getAmountAvailable() < buyDto.getNumber()) {
			throw new ProductDoesNotExistInStockException("Not enough products in the stock.");
		}

		VendingMachineUser vendingMachineUser = userRepository
				.findVendingMachineUserByUserName(authenticationFacade.getAuthentication().getName());

		if (vendingMachineUser.getDeposit() < (product.getCost() * buyDto.getNumber())) {
			throw new NotEnoughMoneyException("Not enough money.");
		}

		int userDeposit = vendingMachineUser.getDeposit();
		vendingMachineUser.setDeposit(userDeposit - (product.getCost() * buyDto.getNumber()));

		Long availableProducts = product.getAmountAvailable();
		product.setAmountAvailable(availableProducts - buyDto.getNumber());

		BuyProductDto buyProductDto = new BuyProductDto();
		buyProductDto.setPurchasedProduct(product.getProductName());
		buyProductDto.setSpentMoney(product.getCost() * buyDto.getNumber());
		buyProductDto.setChanges(userDeposit - (product.getCost() * buyDto.getNumber()));

		return buyProductDto;
	}
}
