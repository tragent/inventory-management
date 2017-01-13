package com.tragent.inventory.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tragent.inventory.auth.service.TokenService;
import com.tragent.inventory.model.User;
import com.tragent.inventory.service.UserService;

@RestController
@RequestMapping("/api/v1/authenticate")
public class AuthenticationController {
	
	@Autowired
	UserService userService;
	private TokenService tokenService = new TokenService();
	
	/**
	 * Return user's information, on successful login authentication.
	 * 
	 * @return user's data
	 */
	@RequestMapping(method=RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> authenticate(@RequestParam(value = "username", required = false) String username) {
		
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByUsername(authentication.getName());
		return new ResponseEntity<User>(user, HttpStatus.OK);
		
	}
	
	/**
	 * Delete user's token on logout
	 * 
	 * @return 204, .
	 */
	@RequestMapping(method=RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(){
		
		tokenService.evictTokens();
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		
	}
	
}
