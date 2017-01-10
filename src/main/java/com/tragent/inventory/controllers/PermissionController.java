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
import com.tragent.inventory.service.PermissionService;

@RestController
@RequestMapping("api/v1/permissions")
public class PermissionController {

	@Autowired
	PermissionService permissionService;
	
	/**
	 * Get all permissions.
	 * 
	 * @return all permissions in the system
	 */
	@RequestMapping(method=RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Permission>> getPermissions(@RequestParam(value = "name", required = false) String name){
		
		Collection<Permission> permissions = new ArrayList<Permission>();
		if (name != null) {
			Permission permission = permissionService.findByName(name);
			permissions.add(permission);
		} else {
			Collection<Permission> allPermissions = permissionService.findAll();
			permissions.addAll(allPermissions);
		}
		return new ResponseEntity<Collection<Permission>>(permissions, HttpStatus.OK);
		
	}
	
	/**
	 * Get permission with given permission id.
	 * 
	 * @param id permission's id
	 * @return the permission with given id or 404 if id is not found
	 */
	@RequestMapping(value="/{id}",
			method=RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Permission> getPermissionById(@PathVariable("id") long id){
		
		Permission permission = permissionService.findById(id);
		if (permission == null) {
			return new ResponseEntity<Permission>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Permission>(permission, HttpStatus.OK);
		
	}
	
	/**
	 * Create new permission
	 * 
	 * @param permission
	 * @return the created permission and HttpStatus.CREATED if permission was successfully created
	 */
	@RequestMapping(method=RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Permission> createPermission(@RequestBody Permission permission){
		
		permission = permissionService.create(permission);
		if (permission == null) {
			return new ResponseEntity<Permission>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<Permission>(permission, HttpStatus.CREATED);
		
	}
	
	/**
	 * update permission's information
	 * 
	 * @param id, permission's id
	 * @return the updated permission's information.
	 */
	@RequestMapping(value="/{id}",
			method=RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Permission> updatePermission(@RequestBody Permission permission){
		
		permission = permissionService.update(permission);
		if (permission == null) {
			return new ResponseEntity<Permission>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<Permission>(permission, HttpStatus.OK);
		
	}
	
	/**
	 * Delete permission's information
	 * 
	 * @param id, permission's id
	 * @return 204, .
	 */
	@RequestMapping(value="/{id}",
			method=RequestMethod.DELETE)
	public ResponseEntity<Permission> deletePermission(@PathVariable("id") Long id){
		
		permissionService.delete(id);
		return new ResponseEntity<Permission>(HttpStatus.NO_CONTENT);
		
	}
	
}
