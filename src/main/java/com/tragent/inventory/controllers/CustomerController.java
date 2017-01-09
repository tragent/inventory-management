package com.tragent.inventory.controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tragent.inventory.model.Customer;
import com.tragent.inventory.model.User;
import com.tragent.inventory.service.CustomerService;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	/**
	 * Get all customers.
	 * 
	 * @return all customers  or all all customers with a particular name in the system
	 */
	@RequestMapping(
			method=RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Customer>> getCustomers(){
		
		Collection<Customer> customers = new ArrayList<Customer>();
		Collection<Customer> customer = customerService.findAll();
		customers.addAll(customer);
		
		return new ResponseEntity<Collection<Customer>>(customers, HttpStatus.OK);	
	}
	
	/**
	 * Get customer with given customer id.
	 * 
	 * @param id id of the customer to return
	 * @return the customer with given id or 404 if id is not found
	 */
	@RequestMapping(
			value="/{id}",
			method=RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long id){
		
		Customer customer = customerService.findById(id);
		if (customer == null) {
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		
	}
	
	/**
	 * Create new customer
	 * 
	 * @param customer
	 * @return the created customer and HttpStatus.CREATED if customer was successfully created
	 */
	@RequestMapping(
			method=RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
		
		customer = customerService.create(customer);
		if (customer == null) {
			return new ResponseEntity<Customer>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
		
	}
	
	/**
	 * update customer's information
	 * 
	 * @param id, id of the customer
	 * @return the updated customer information.
	 */
	@RequestMapping(value="/{id}",
			method=RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> updateUser(@RequestBody Customer customer){
		
		customer = customerService.update(customer);
		if (customer == null) {
			return new ResponseEntity<Customer>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		
	}
	
	/**
	 * Activate or deactivate customer
	 * 
	 * @param id, id of the customer
	 * @return 204, .
	 */
	@RequestMapping(value="/{id}",
			method=RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable("id") Long id){
		
		customerService.delete(id);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		
	}
}
