package com.mvp.mvpbackendchallenge.repositories;

import com.mvp.mvpbackendchallenge.entities.VendingMachineUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<VendingMachineUserRole, Long> {
	VendingMachineUserRole findVendingMachineUserRoleByRoleName(String roleName);
}
