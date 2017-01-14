package com.tragent.inventory.service;

import java.util.Collection;
import com.tragent.inventory.model.Product;

/* Service that provides CRUD operation for Products  */
public interface ProductService {
	
	/**
	 * Get all products in the system.
	 * 
	 * @return collection of all product in the system
	 */
	public Collection<Product> findAll();
	
	/**
	 * Get all active products in the system.
	 * 
	 * @return collection of all product in the system
	 */
	public Collection<Product> findByIsActive();
	
	/**
	 * Find a product by Id.
	 * 
	 * @param id the product's id
	 * @return the product object if found else return null
	 */
	public Product findById(Long id);
	
	/**
	 * Find a product by name.
	 * 
	 * @param name the product's name
	 * @return the product object if found else return null
	 */
	public Product findByName(String name);
	
	/**
	 * Create new Product.
	 * 
	 * @param  the product to be created
	 * @return the created product 
	 */
	public Product create(Product product);
	
	/**
	 * Update an existing product's information.
	 * 
	 * @param product the updated product's record
	 * @return the updated product object
	 */
	public Product update(Product product);
	
	/**
	 * Delete a product from the system.
	 * 
	 * @param id product's id
	 */
	public void delete(Long id);

}