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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tragent.inventory.model.Permission;
import com.tragent.inventory.model.Role;
import com.tragent.inventory.service.RoleService;

@RestController
@RequestMapping("api/v1/roles")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	/**
	 * Get all roles.
	 * 
	 * @return all roles in the system
	 */
	@RequestMapping(method=RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Role>> getRoles(@RequestParam(value = "name", required = false) String name){
		
		Collection<Role> roles = new ArrayList<Role>();
		if (name != null) {
			Role role = roleService.findByName(name);
			roles.add(role);
		} else {
			Collection<Role> allRoles = roleService.findAll();
			roles.addAll(allRoles);
		}
		return new ResponseEntity<Collection<Role>>(roles, HttpStatus.OK);
		
	}
	
	/**
	 * Get role with given role id.
	 * 
	 * @param id role's id
	 * @return the role with given id or 404 if id is not found
	 */
	@RequestMapping(value="/{id}",
			method=RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Role> getRoleById(@PathVariable("id") int id){
		
		Role role = roleService.findById(id);
		if (role == null) {
			return new ResponseEntity<Role>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Role>(role, HttpStatus.OK);
		
	}
	
	/**
	 * Get permissions belonging to role with id.
	 * 
	 * @param id role's id
	 * @return the permissions with given id or 404 if id is not found
	 */
	@RequestMapping(value="/{id}/permissions",
			method=RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Permission>> getPemissionsformRole(@PathVariable("id") int id){
		
		Role role = roleService.findById(id);
		if (role == null) {
			return new ResponseEntity<Collection<Permission>>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Collection<Permission>>(role.getPermission(), HttpStatus.OK);
		
	}
	
	/**
	 * Create new role
	 * 
	 * @param role
	 * @return the created role and HttpStatus.CREATED if role was successfully created
	 */
	@RequestMapping(method=RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Role> createRole(@RequestBody Role role){
		
		role = roleService.create(role);
		if (role == null) {
			return new ResponseEntity<Role>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<Role>(role, HttpStatus.CREATED);
		
	}
	
	/**
	 * update role's information
	 * 
	 * @param id, role's id
	 * @return the updated role's information.
	 */
	@RequestMapping(value="/{id}",
			method=RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Role> updateRole(@RequestBody Role role){
		
		role = roleService.update(role);
		if (role == null) {
			return new ResponseEntity<Role>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<Role>(role, HttpStatus.OK);
		
	}
	
	/**
	 * add permission to role.
	 * 
	 * @param roleId, pemissionId role's id and pemission's id
	 * @return the updated list of permissions belong to role
	 */
	@RequestMapping(value="/{roleId}/permissions/{permissionId}",
			method=RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Permission>> addPemissiontoRole(@PathVariable("roleId") int roleId, @PathVariable("permissionId") Long permissionId){
		
		Collection<Permission> permissions = roleService.updatePermission(roleId, permissionId);
		if (permissions == null) {
			return new ResponseEntity<Collection<Permission>>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Collection<Permission>>(permissions, HttpStatus.OK);
		
	}
	
	/**
	 * Delete category's information
	 * 
	 * @param id, category's id
	 * @return 204, .
	 */
	@RequestMapping(value="/{id}",
			method=RequestMethod.DELETE)
	public ResponseEntity<Role> deleteRole(@PathVariable("id") int id){
		
		roleService.delete(id);
		return new ResponseEntity<Role>(HttpStatus.NO_CONTENT);
		
	}
	
}
