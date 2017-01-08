package com.tragent.inventory.service;

import java.util.Collection;

import com.tragent.inventory.model.User;

/* Service that provides CRUD operation for Users  */
public interface UserService {
	
	/**
	 * Get all users in the system.
	 * 
	 * @return collection of all Users in the system
	 */
	Collection<User> findAll();
	
	/**
	 * Find a user by Id.
	 * 
	 * @param id the id of the required user
	 * @return the user object if found else return null
	 */
	User findById(Long id);
	
	/**
	 * Find a user by User.
	 * 
	 * @param username the username of the required user
	 * @return the user object if found else return null
	 */
	User findByUsername(String username);
	
	/**
	 * Find a user by email.
	 * 
	 * @param id the id of the user to get
	 * @return the user object if found else return null
	 */
	User findByEmail(String email);
	
	/**
	 * Create new user.
	 * 
	 * @param user the user to be created
	 * @return the user created
	 */
	User create(User user);
	
	/**
	 * Update an existing user's information.
	 * 
	 * @param user the Updated user record
	 * @return the updated user object
	 */
	User update(User user);
	
	/**
	 * Delete a user from the system.
	 * 
	 * @param id id of the user to be deleted
	 */
	void delete(Long id);
	
}
