package com.tragent.inventory.repository;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.tragent.inventory.model.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
	
	Collection<Purchase> findBytransactionDate(@Param("date") Date date);

}
