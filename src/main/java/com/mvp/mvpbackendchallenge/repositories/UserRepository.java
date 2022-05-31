package com.mvp.mvpbackendchallenge.repositories;

import com.mvp.mvpbackendchallenge.entities.VendingMachineUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<VendingMachineUser, Long> {

	VendingMachineUser findVendingMachineUserByUserName(String userName);

	VendingMachineUser deleteVendingMachineUserByUserName(String userName);
}
