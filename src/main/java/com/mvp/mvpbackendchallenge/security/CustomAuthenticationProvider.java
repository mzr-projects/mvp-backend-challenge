package com.mvp.mvpbackendchallenge.security;

import com.mvp.mvpbackendchallenge.entities.VendingMachineUser;
import com.mvp.mvpbackendchallenge.entities.VendingMachineUserRole;
import com.mvp.mvpbackendchallenge.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String userName = authentication.getName();
		String password = authentication.getCredentials().toString();

		VendingMachineUser user = userRepository.findVendingMachineUserByUserName(userName);

		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}

		Set<VendingMachineUserRole> roles = user.getVendingMachineUserRoleSet();
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (VendingMachineUserRole role : roles) {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
		}

		return new UsernamePasswordAuthenticationToken(userName, password, authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
