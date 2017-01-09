package com.tragent.inventory.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.tragent.inventory.model.Category;

/**
 * Repository used by CategoryService to access database.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	Category findByName(@Param("name") String name);
	
	Collection<Category> findBycategoryEnable(@Param("categoryEnable") boolean categoryEnable);
}
