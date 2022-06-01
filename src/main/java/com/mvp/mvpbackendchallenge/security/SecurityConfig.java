package com.mvp.mvpbackendchallenge.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

	private final CustomAuthenticationProvider customAuthenticationProvider;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.httpBasic()
				.and()
				.csrf()
				.disable()
				.authenticationProvider(customAuthenticationProvider)
				.authorizeRequests()
				.antMatchers("/mvp/vending/user/register")
				.permitAll()
				.and()
				.authorizeRequests()
				.antMatchers("/h2-console/**")
				.permitAll()
				.and()
				.authorizeRequests()
				.anyRequest().authenticated();

		http.headers().frameOptions().disable();
		return http.build();
	}
}
