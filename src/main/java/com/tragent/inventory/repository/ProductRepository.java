package com.tragent.inventory.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.tragent.inventory.model.Product;

/**
 * Repository used by productService to access database.
 */
public interface ProductRepository  extends JpaRepository<Product, Long>{
	
    Product findByName(@Param("name") String name);
	
	Collection<Product> findByIsActive(@Param("isActive") boolean isActive);

}
