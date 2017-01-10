package com.tragent.inventory.service;

import java.util.Collection;

import com.tragent.inventory.model.Category;

/* Service that provides CRUD operation for Categories  */
public interface CategoryService {
	
	/**
	 * Get all categories in the system.
	 * 
	 * @return collection of all categories in the system
	 */
	public Collection<Category> findAll();
	
	/**
	 * Find a category by Id.
	 * 
	 * @param id the category's id
	 * @return the category object if found else return null
	 */
	public Category findById(Long id);
	
	/**
	 * Find a category by name.
	 * 
	 * @param name the category's name
	 * @return the category object if found else return null
	 */
	public Category findByName(String name);
	
	/**
	 * Create new Category.
	 * 
	 * @param category the category to be created
	 * @return the created category 
	 */
	public Category create(Category category);
	
	/**
	 * Update an existing category's information.
	 * 
	 * @param category the updated category's record
	 * @return the updated category object
	 */
	public Category update(Category category);
	
	/**
	 * Delete a category from the system.
	 * 
	 * @param id category's id
	 */
	public void delete(Long id);
}
