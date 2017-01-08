package com.tragent.inventory.auth.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

@SuppressWarnings("serial")
public class AuthenticationWithToken extends PreAuthenticatedAuthenticationToken {

	public AuthenticationWithToken(Object aPrincipal, Object aCredentials) {
		super(aPrincipal, aCredentials);
	}

	public AuthenticationWithToken(Object aPrincipal, Object aCredentials, Collection<? extends GrantedAuthority> anAuthorities) {
        super(aPrincipal, aCredentials, anAuthorities);
    }

    public void setToken(String token) {
        setDetails(token);
    }

    public String getToken() {
        return (String)getDetails();
    }

}
