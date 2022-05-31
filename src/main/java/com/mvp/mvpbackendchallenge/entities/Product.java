package com.mvp.mvpbackendchallenge.entities;

import com.mvp.mvpbackendchallenge.validators.ProductCostConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, unique = true)
	private String productName;

	@Column(nullable = false)
	private Long amountAvailable;

	@Column(nullable = false)
	private int cost;

	@Column(nullable = false)
	private Long sellerId;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;

		if (o == null || getClass() != o.getClass()) return false;

		Product product = (Product) o;

		return new EqualsBuilder().append(productName, product.productName).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(productName).toHashCode();
	}
}
