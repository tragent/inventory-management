package com.tragent.inventory.service.implementation;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tragent.inventory.model.Product;
import com.tragent.inventory.model.Purchase;
import com.tragent.inventory.model.Supplier;
import com.tragent.inventory.repository.PurchaseRepository;
import com.tragent.inventory.service.ProductService;
import com.tragent.inventory.service.PurchaseService;
import com.tragent.inventory.service.SupplierService;

@Service
public class PurchaseServiceImpl implements PurchaseService {
	
	@Autowired
	private PurchaseRepository purchaseRepository;
	
	private SupplierService supplierService;
	private ProductService productService;

	@Override
	public Collection<Purchase> findAll() {
		
		Collection<Purchase> purchase = purchaseRepository.findAll();
		return purchase;
		
	}

	@Override
	public Collection<Purchase> findByDate(Date date) {
		
		Collection<Purchase> purchase = purchaseRepository.findBytransactionDate(date);
		return purchase;
		
	}

	@Override
	public Purchase create(Long supplierId, Long productId, Integer quantity) {
		
		Supplier supplier = supplierService.findById(supplierId);
		Product product = productService.findById(productId);
		Purchase purchase = new Purchase(product, supplier, quantity);
		purchaseRepository.save(purchase);
		return purchase;
		
	}
	
}
