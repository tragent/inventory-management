package com.tragent.inventory.service;

import java.util.Collection;
import java.util.Map;

import com.tragent.inventory.model.Sale;

public interface SaleService {
	
	/**
	 * Get all sales transactions in the system.
	 * 
	 * @return collection of all sales transactions in the system
	 */
	public Collection<Sale> findAll();
	
	/**
	 * Find a sales by transaction date.
	 * 
	 * @param id the sales' id
	 * @return the transaction object if found else return null
	 */
	public Sale findById(Long id);
	
	/**
	 * Create new sales.
	 * 
	 * @param sale the sales to be created
	 * @return the created sale
	 */
	public Sale create(Long quantity, Long agentId, Long productId, Long customerId);
	
	/**
	 * Add product in cart
	 * 
	 */
	public Map<Long, Long> getCart();
	
	/**
	 * Add  new sales.
	 * 
	 * @param sale the sales to be created
	 * @return the created sale
	 */
	public Sale addProduct(Sale sale);
	 
}
