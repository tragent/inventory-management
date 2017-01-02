package com.tragent.inventory.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tragent.inventory.model.User;
import com.tragent.inventory.service.UserService;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * Get all uses.
	 * 
	 * @return all users in the system
	 */
	@RequestMapping(method=RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<User>> getUsers(){
		
		Collection<User> users = userService.findAll();
		
		return new ResponseEntity<Collection<User>>(users, HttpStatus.OK);
	}
	
	/**
	 * Get user with given user id.
	 * 
	 * @param id id of the user to return
	 * @return the user with given id or 404 if id is not found
	 */
	@RequestMapping(value="/{id}",
			method=RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUserById(@PathVariable("id") Long id){
		
		User user = userService.findById(id);
		if(user == null){
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	/**
	 * Get user with given username or email.
	 * 
	 * @param username, email  username or email of the user to return
	 * @return the user with given username or email
	 */
	@RequestMapping(params = {"username","email"},
			method=RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUserByUsername(@RequestParam(value = "username", required = false) String username, @RequestParam(value = "email", required = false) String email){
		
		if(username != null){
			User user = userService.findByUsername(username);
			
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		User user = userService.findByEmail(email);
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
		
	}
	
	/**
	 * Create new user
	 * 
	 * @param user
	 * @return the created user and HttpStatus.CREATED if user was successfully created
	 */
	@RequestMapping(method=RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> createUser(@RequestBody User user){
		
		user = userService.create(user);
		
		if(user == null){
			return new ResponseEntity<User>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
		
	}
	
	/**
	 * update user's information
	 * 
	 * @param id, id of the user
	 * @return the updated user information.
	 */
	@RequestMapping(value="/{id}",
			method=RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> updateUser(@RequestBody User user){
		
		user = userService.update(user);
		
		if(user == null){
			return new ResponseEntity<User>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
		
	}
	
	/**
	 * Delete user's information
	 * 
	 * @param id, id of the user
	 * @return 204, .
	 */
	@RequestMapping(value="/{id}",
			method=RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable("id") Long id){
		
		userService.delete(id);
		
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		
	}
}
