package com.tragent.inventory.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.tragent.inventory.model.Role;

/**
 * Repository used by RoleService to access database.
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByName(@Param("name") String name);
	
	Collection<Role> findByroleEnable(@Param("roleEnable") boolean roleEnable);
	
	Role findByid(@Param("id") int id);
	
}
