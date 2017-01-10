package com.tragent.inventory.service;

import java.util.Collection;

import com.tragent.inventory.model.Permission;

public interface PermissionService {
	/**
	 * Get all permissions in the system.
	 * 
	 * @return collection of all permissions in the system
	 */
	public Collection<Permission> findAll();
	
	/**
	 * Find a permission by Id.
	 * 
	 * @param id the permission's id
	 * @return the permission object if found else return null
	 */
	public Permission findById(Long id);
	
	/**
	 * Find a permission by name.
	 * 
	 * @param name the permission's name
	 * @return the permission object if found else return null
	 */
	public Permission findByName(String name);
	
	/**
	 * Create new Permission.
	 * 
	 * @param permission the permission to be created
	 * @return the created permission
	 */
	public Permission create(Permission permission);
	
	/**
	 * Update an existing permission's information.
	 * 
	 * @param permission the updated permission's record
	 * @return the updated permission object
	 */
	public Permission update(Permission permission);
	
	/**
	 * Delete a permission from the system.
	 * 
	 * @param id permission's id
	 */
	public void delete(Long id);
}
