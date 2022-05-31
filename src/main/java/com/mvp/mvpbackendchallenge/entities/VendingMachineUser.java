package com.mvp.mvpbackendchallenge.entities;


import com.mvp.mvpbackendchallenge.validators.CoinAmountConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VendingMachineUser implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String userName;

	private String password;

	private int deposit;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name = "vending_machine_users_roles",
			joinColumns = @JoinColumn(name = "vending_machine_user_id"),
			inverseJoinColumns = @JoinColumn(name = "vending_machine_user_role_id")
	)
	private Set<VendingMachineUserRole> vendingMachineUserRoleSet;
}
