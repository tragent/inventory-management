package com.tragent.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.tragent.inventory.model.Supplier;

/**
 * Repository used by SupplierService to access database.
 */
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
	
	Supplier findByEmail(@Param("email") String email);
	
}
