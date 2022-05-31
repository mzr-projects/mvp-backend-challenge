package com.mvp.mvpbackendchallenge.repositories;

import com.mvp.mvpbackendchallenge.entities.VendingMachineUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<VendingMachineUserRole,Long> {
}
