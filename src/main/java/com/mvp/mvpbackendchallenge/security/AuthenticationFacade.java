package com.mvp.mvpbackendchallenge.security;

import org.springframework.security.core.Authentication;

public interface AuthenticationFacade {
	Authentication getAuthentication();
}
