package com.tragent.inventory.service.implementation;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tragent.inventory.model.Customer;
import com.tragent.inventory.repository.CustomerRepository;
import com.tragent.inventory.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Collection<Customer> findAll() {
		Collection<Customer> customers = customerRepository.findAll();
		return customers;
	}

	@Override
	public Customer findById(Long id) {
		Customer customer = customerRepository.findOne(id);
		return customer;
	}

	@Override
	public Customer findByPhoneNumber(String phoneNumber) {
		Customer customer = customerRepository.findByPhoneNumber(phoneNumber);
		return customer;
	}

	@Override
	public Customer create(Customer customer) {
		if (customer.getPhoneNumber() == null || findByPhoneNumber(customer.getPhoneNumber()) != null) {
			return null;
		}
		
		customerRepository.save(customer);
		return customer;
	}

	@Override
	public Customer update(Customer customer) {
		Customer customerPersisted = findById(customer.getId());
		if (customerPersisted == null || customer.getPhoneNumber() == null) {
			//Cannot update customer that doesn't exist or customer without phone Number
			return null;
		}
		
		customerRepository.save(customer);
		return customer;
	}

	@Override
	public void delete(Long id) {
		Customer customer = findById(id);
		if (customer == null) {
			return;
		}
		if(customer.getIsActive() == true){
			customer.setIsActive(false);
		}
		else{
			customer.setIsActive(true);
		}
		customerRepository.save(customer);
		
	}

	@Override
	public Collection<Customer> findByIsActive() {
		Collection<Customer> customers = customerRepository.findByIsActive(true);
		return customers;
	}

	

}