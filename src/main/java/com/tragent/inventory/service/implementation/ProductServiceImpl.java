package com.tragent.inventory.service.implementation;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tragent.inventory.model.Product;
import com.tragent.inventory.repository.ProductRepository;
import com.tragent.inventory.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Collection<Product> findAll() {
		Collection<Product> product = productRepository.findAll();
		return product;
	}

	@Override
	public Product findById(Long id) {
		Product product = productRepository.findOne(id);
		return product;
	}

	@Override
	public Product findByName(String name) {
		Product product = productRepository.findByName(name);
		return product;
	}

	@Override
	public Product create(Product product) {
		
		if (product.getName() == null) {
			//cannot create product without a name
			return null;
		}
		
		productRepository.save(product);
		return product;
	}

	@Override
	public Product update(Product product) {
		Product productPersisted = findById(product.getId());
		if (productPersisted == null) {
			//cannot update product without a category name
			return null;
		}
		
		productRepository.save(product);
		return product;
	}

	@Override
	public void delete(Long id) {
		Product product = findById(id);
		if (product == null) {
			return;
		}
		if(product.isActive() == true){
			product.setIsActive(false);
		}
		else{
			product.setIsActive(true);
		}
		productRepository.save(product);
		
	}

	@Override
	public Collection<Product> findByIsActive() {
		
		Collection<Product> product = productRepository.findByIsActive(true);
		return product;
		
	}

}