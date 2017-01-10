package com.tragent.inventory.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.tragent.inventory.model.Permission;

/**
 * Repository used by PermissionService to access database.
 */
public interface PermissionRepository extends JpaRepository<Permission, Long> {
	
	Permission findByName(@Param("name") String name);
	
	Collection<Permission> findBypermissionEnable(@Param("permissionEnable") boolean permissionEnable);
	
}
