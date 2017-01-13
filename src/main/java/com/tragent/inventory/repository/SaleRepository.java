package com.tragent.inventory.repository;


import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tragent.inventory.model.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
	
	Collection<Sale> findByTransactionDate(Date date);

}
