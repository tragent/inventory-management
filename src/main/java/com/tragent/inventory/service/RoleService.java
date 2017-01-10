package com.tragent.inventory.service;

import java.util.Collection;

import com.tragent.inventory.model.Permission;
import com.tragent.inventory.model.Role;

public interface RoleService {
	
	/**
	 * Get all roles in the system.
	 * 
	 * @return collection of all roles in the system
	 */
	public Collection<Role> findAll();
	
	/**
	 * Find a role by Id.
	 * 
	 * @param id the role's id
	 * @return the role object if found else return null
	 */
	public Role findById(int id);
	
	/**
	 * Find a role by name.
	 * 
	 * @param name the role's name
	 * @return the role object if found else return null
	 */
	public Role findByName(String name);
	
	/**
	 * Create new Role.
	 * 
	 * @param role the role to be created
	 * @return the created role
	 */
	public Role create(Role role);
	
	/**
	 * Update an existing role's information.
	 * 
	 * @param role the updated role's record
	 * @return the updated role object
	 */
	public Role update(Role role);
	
	/**
	 * Add new permission to a role.
	 * 
	 * @param roleId, permissionId
	 * @return the updated collection of permission
	 */
	public Collection<Permission> addPermission(int roleId, Long permissionId);
	
	/**
	 * Delete a role from the system.
	 * 
	 * @param id role's id
	 */
	public void delete(int id);
	
}
