package com.mvp.mvpbackendchallenge.security;

import com.mvp.mvpbackendchallenge.entities.VendingMachineUser;
import com.mvp.mvpbackendchallenge.entities.VendingMachineUserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class VendingMachineUserDetails implements UserDetails {
	private VendingMachineUser vendingMachineUser;

	public VendingMachineUserDetails(VendingMachineUser vendingMachineUser) {
		this.vendingMachineUser = vendingMachineUser;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<VendingMachineUserRole> roles = vendingMachineUser.getVendingMachineUserRoleSet();
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();

		for (VendingMachineUserRole role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		}

		return authorities;
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}
}
