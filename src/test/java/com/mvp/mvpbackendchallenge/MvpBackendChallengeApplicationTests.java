package com.mvp.mvpbackendchallenge;

import com.mvp.mvpbackendchallenge.entities.Product;
import com.mvp.mvpbackendchallenge.paloads.BuyDto;
import com.mvp.mvpbackendchallenge.paloads.BuyProductDto;
import com.mvp.mvpbackendchallenge.paloads.DepositDto;
import com.mvp.mvpbackendchallenge.paloads.ProductDto;
import com.mvp.mvpbackendchallenge.repositories.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MvpBackendChallengeApplicationTests {
	@LocalServerPort
	int randomServerPort;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void testDeposit_success() throws URISyntaxException {
		final String baseUrl = "http://localhost:" + randomServerPort + "/mvp/vending/user/deposit";
		URI uri = new URI(baseUrl);

		DepositDto depositDto = new DepositDto();
		depositDto.setUserName("buyer_user");
		depositDto.setDeposit(100);
		ResponseEntity<String> result = this.restTemplate
				.withBasicAuth("buyer_user", "mtp")
				.postForEntity(uri, depositDto, String.class);

		Assertions.assertEquals(200, result.getStatusCodeValue());
	}

	@Test
	void testCreateProduct_success() throws URISyntaxException {
		final String baseUrl = "http://localhost:" + randomServerPort + "/mvp/vending/products/add";
		URI uri = new URI(baseUrl);

		ProductDto productDto = new ProductDto();
		productDto.setProductName("LapTop");
		productDto.setSellerId(10l);
		productDto.setCost(120);
		productDto.setAmountAvailable(10l);

		ResponseEntity<String> result = this.restTemplate
				.withBasicAuth("admin", "mtp")
				.postForEntity(uri, productDto, String.class);

		Product product = productRepository.findProductByProductName("LapTop");

		Assertions.assertEquals(201, result.getStatusCodeValue());
		Assertions.assertEquals(product.getProductName(), "LapTop");
	}

	@Test
	void testBuy_success() throws URISyntaxException {
		final String baseUrl = "http://localhost:" + randomServerPort + "/mvp/vending/user-products/buy";
		URI uri = new URI(baseUrl);

		testCreateProduct_success();

		BuyDto buyDto = new BuyDto();
		buyDto.setProductName("LapTop");
		buyDto.setNumber(2);
		ResponseEntity<BuyProductDto> result = this.restTemplate
				.withBasicAuth("buyer_user", "mtp")
				.postForEntity(uri, buyDto, BuyProductDto.class);

		Assertions.assertEquals(200, result.getStatusCodeValue());
		Assertions.assertEquals(760, Objects.requireNonNull(result.getBody()).getChanges());
	}
}
