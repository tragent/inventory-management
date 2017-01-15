package com.tragent.inventory.service;

import java.util.Collection;

import com.tragent.inventory.model.Customer;

public interface CustomerService {
	
	/**
	 * Get all customers in the system.
	 * 
	 * @return collection of all Suppliers in the system
	 */
	Collection<Customer> findAll();
	
	/**
	 * Get all active customers in the system.
	 * 
	 * @return collection of all Suppliers in the system
	 */
	Collection<Customer> findByIsActive();
	
	/**
	 * Find a customer by Id.
	 * 
	 * @param id the id of the required supplier
	 * @return the supplier object if found else return null
	 */
	Customer findById(Long id);
	
	/**
	 * Find a supplier by phone number.
	 * 
	 * @param phoneNumber the email of the supplier to be gotten
	 * @return the Customer object if found else return null
	 */
	Customer findByPhoneNumber(String phoneNumber);
	
	/**
	 * Create new Customer.
	 * 
	 * @param user the Customer to be created
	 * @return customer
	 */
	Customer create(Customer customer);
	
	/**
	 * Update an existing customer's information.
	 * 
	 * @param Customer the Updated user record
	 * @return the updated customer object
	 */
	Customer update(Customer customer);
	
	/**
	 * Delete a Customer from the system.
	 * 
	 * @param id id of the customer to be activated or deactivated
	 */
	void delete(Long id);
	

}