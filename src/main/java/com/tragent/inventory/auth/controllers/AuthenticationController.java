package com.tragent.inventory.auth.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
	
	@RequestMapping(value = "/api/v1/authenticate", method = RequestMethod.POST)
    public String authenticate() {
		return "Auth is working";
	}
}
