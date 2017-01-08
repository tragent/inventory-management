package com.tragent.inventory.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.tragent.inventory.model.User;
import com.tragent.inventory.service.UserService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private UserService userservice;
	private TokenService tokenService = new TokenService();

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();
		
		User user = userservice.findByUsername(username);
		
		if (user == null) {
			throw new BadCredentialsException("Username not found."+username);
		}
		
		if ( !password.equals(user.getPassword()) ) {
			throw new BadCredentialsException("Wrong password.");
		}
		
		String newToken = tokenService.generateNewToken();
		AuthenticationWithToken resultOfAuthentication = new AuthenticationWithToken(authentication.getPrincipal(),authentication.getCredentials());
		
        resultOfAuthentication.setToken(newToken);
        tokenService.store(newToken, resultOfAuthentication);
        resultOfAuthentication.setAuthenticated(true);

        return resultOfAuthentication;
        
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
}
