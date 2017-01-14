package com.tragent.inventory.service;

import java.util.Collection;

import com.tragent.inventory.model.Supplier;

/* Service that provides CRUD operation for Suppliers  */
public interface SupplierService {

	/**
	 * Get all Suppliers in the system.
	 * 
	 * @return collection of all Suppliers in the system
	 */
	public Collection<Supplier> findAll();
	
	/**
	 * Get all Suppliers with accounts enabled in the system.
	 * 
	 * @return collection of all Suppliers in the system
	 */
	public Collection<Supplier> findByaccountEnabled();
	
	/**
	 * Find a supplier by Id.
	 * 
	 * @param id the id of the required supplier
	 * @return the supplier object if found else return null
	 */
	public Supplier findById(Long id);
		
	/**
	 * Find a supplier by email.
	 * 
	 * @param email the email of the supplier to be gotten
	 * @return the Supplier object if found else return null
	 */
	public Supplier findByEmail(String email);
	
	/**
	 * Create new supplier.
	 * 
	 * @param supplier the Supplier to be created
	 * @return the supplier created
	 */
	public Supplier create(Supplier supplier);
	
	/**
	 * Update an existing supplier's information.
	 * 
	 * @param Supplier the updated supplier record
	 * @return the updated supplier object
	 */
	public Supplier update(Supplier supplier);
	
	/**
	 * Delete a supplier from the system.
	 * 
	 * @param id id of the supplier to be deleted
	 */
	public void delete(Long id);
	
}