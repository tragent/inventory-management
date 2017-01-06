package com.tragent.inventory.auth.service;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.UrlPathHelper;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AuthenticationFilter extends GenericFilterBean {
	
    public static final String TOKEN_SESSION_KEY = "token";
    public static final String USER_SESSION_KEY = "user";
    private AuthenticationManager authenticationManager;
    public static final String AUTHENTICATE_URL = "/api/v1/authenticate";
    
	 
	 public AuthenticationFilter(AuthenticationManager authenticationManager) {
		 this.authenticationManager = authenticationManager;
	 }

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
				
		HttpServletRequest httpRequest = asHttp(request);
        HttpServletResponse httpResponse = asHttp(response);
        
        String username = httpRequest.getParameter("username");
        String password = httpRequest.getParameter("password");
        String token = httpRequest.getHeader("X-Auth-Token");
        
        
        String resourcePath = new UrlPathHelper().getPathWithinApplication(httpRequest);
        
        try {
            if (postToAuthenticate(httpRequest, resourcePath)) {
                processUsernamePasswordAuthentication(httpResponse, username, password);
                return;
            }
            
            else if (!(token == null) && !token.isEmpty()) {
                processTokenAuthentication(token);
            }
            
            chain.doFilter(request, response);
        } catch (InternalAuthenticationServiceException internalAuthenticationServiceException) {
            SecurityContextHolder.clearContext();
            httpResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (AuthenticationException authenticationException) {
            SecurityContextHolder.clearContext();
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, authenticationException.getMessage());
        } finally {
            MDC.remove(TOKEN_SESSION_KEY);
            MDC.remove(USER_SESSION_KEY);
        }
		
	}

	private void processTokenAuthentication(String token) {
		
		Authentication resultOfAuthentication = tryToAuthenticateWithToken(token);
        SecurityContextHolder.getContext().setAuthentication(resultOfAuthentication);
	}

	private Authentication tryToAuthenticateWithToken(String token) {
		
		PreAuthenticatedAuthenticationToken requestAuthentication = new PreAuthenticatedAuthenticationToken(token, null);
        return tryToAuthenticate(requestAuthentication);
	}

	private void processUsernamePasswordAuthentication(HttpServletResponse httpResponse, String username,
			String password) throws IOException {
		
		Authentication resultOfAuthentication = tryToAuthenticateWithUsernameAndPassword(username, password);
		SecurityContextHolder.getContext().setAuthentication(resultOfAuthentication);
        httpResponse.setStatus(HttpServletResponse.SC_OK);
        TokenResponse tokenResponse = new TokenResponse(resultOfAuthentication.getDetails().toString());
        String tokenJsonResponse = new ObjectMapper().writeValueAsString(tokenResponse);
        httpResponse.addHeader("Content-Type", "application/json");
        httpResponse.getWriter().print(tokenJsonResponse);
		
	}

	private Authentication tryToAuthenticateWithUsernameAndPassword(String username, String password) {

    	//Collection<? extends GrantedAuthority> authorities = user.getRole().getPermission();
		UsernamePasswordAuthenticationToken requestAuthentication = new UsernamePasswordAuthenticationToken(username, password);
		return tryToAuthenticate(requestAuthentication);
        
	}

	private Authentication tryToAuthenticate(Authentication requestAuthentication) {
		
		Authentication responseAuthentication = authenticationManager.authenticate(requestAuthentication);
        if (responseAuthentication == null || !responseAuthentication.isAuthenticated()) {
            throw new InternalAuthenticationServiceException("Unable to authenticate user for provided credentials");
        }
        return responseAuthentication;
        
	}

	private boolean postToAuthenticate(HttpServletRequest httpRequest, String resourcePath) {
		return AUTHENTICATE_URL.equalsIgnoreCase(resourcePath) && httpRequest.getMethod().equals("POST");
	}

	private HttpServletRequest asHttp(ServletRequest request) {
        return (HttpServletRequest) request;
    }

    private HttpServletResponse asHttp(ServletResponse response) {
        return (HttpServletResponse) response;
    }

}
