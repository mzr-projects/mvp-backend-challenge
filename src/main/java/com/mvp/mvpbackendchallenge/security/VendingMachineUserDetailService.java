package com.mvp.mvpbackendchallenge.security;

import com.mvp.mvpbackendchallenge.entities.VendingMachineUser;
import com.mvp.mvpbackendchallenge.entities.VendingMachineUserRole;
import com.mvp.mvpbackendchallenge.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//@Service
@RequiredArgsConstructor
@Slf4j
public class VendingMachineUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		VendingMachineUser user = userRepository.findVendingMachineUserByUserName(username);
		if (user != null) {
			return new org.springframework.security.core.userdetails.User(user.getUserName(),
					user.getPassword(),
					buildSimpleGrantedAuthorities(user.getVendingMachineUserRoleSet()));
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

	private static List<SimpleGrantedAuthority> buildSimpleGrantedAuthorities(final Set<VendingMachineUserRole> roles) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (VendingMachineUserRole role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		}
		return authorities;
	}

}
