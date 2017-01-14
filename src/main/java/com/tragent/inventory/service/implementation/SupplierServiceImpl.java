package com.tragent.inventory.service.implementation;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tragent.inventory.model.Supplier;
import com.tragent.inventory.repository.SupplierRepository;
import com.tragent.inventory.service.SupplierService;

@Service
public class SupplierServiceImpl implements SupplierService{
	
	@Autowired
	private SupplierRepository supplierRepository;

	@Override
	public Collection<Supplier> findAll() {
		
		Collection<Supplier> suppliers = supplierRepository.findAll();
		return suppliers;
		
	}

	@Override
	public Supplier findById(Long id) {
		
		Supplier supplier = supplierRepository.findOne(id);
		return supplier;
		
	}

	@Override
	public Supplier findByEmail(String email) {
		
		Supplier supplier = supplierRepository.findByEmail(email);
		return supplier;
		
	}

	@Override
	public Supplier create(Supplier supplier) {
		
		if (supplier.getEmail() == null || findByEmail(supplier.getEmail()) != null) {
			//cannot create supplier without an email
			return null;
		}
		
		supplierRepository.save(supplier);
		return supplier;
	}

	@Override
	public Supplier update(Supplier supplier) {
		
		Supplier supplierPersisted = findById(supplier.getId());
		if (supplierPersisted == null || supplier.getEmail() == null) {
			//Cannot update supplier that doesn't exist or supplier without email
			return null;
		}
		
		supplierRepository.save(supplier);
		return supplier;
		
	}

	@Override
	public void delete(Long id) {
		
		Supplier supplier = findById(id);
		if (supplier == null) {
			return;
		}
		
		supplier.setAccountEnabled(false);
		supplierRepository.save(supplier);
	}

	@Override
	public Collection<Supplier> findByaccountEnabled() {
		
		Collection<Supplier> suppliers = supplierRepository.findByAccountEnabled(true);
		return suppliers;
		
	}

}