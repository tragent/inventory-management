package com.tragent.inventory.service.implementation;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tragent.inventory.model.Category;
import com.tragent.inventory.repository.CategoryRepository;
import com.tragent.inventory.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Collection<Category> findAll() {
		
		Collection<Category> categories = categoryRepository.findBycategoryEnable(true);
		return categories;
		
	}

	@Override
	public Category findById(Long id) {
		
		Category category = categoryRepository.findOne(id);
		return category;
		
	}

	@Override
	public Category findByName(String name) {
		
		Category category = categoryRepository.findByName(name);
		return category;
	}

	@Override
	public Category create(Category category) {
		
		if (category.getName() == null) {
			//cannot create category without a name
			return null;
		}
		
		categoryRepository.save(category);
		return category;
	}

	@Override
	public Category update(Category category) {
		
		if (category.getName() == null) {
			//cannot update category without a category name
			return null;
		}
		
		categoryRepository.save(category);
		return category;
		
	}

	@Override
	public void delete(Long id) {
		
		Category category = findById(id);
		if (category == null) {
			return;
		}
		
		category.setCategoryEnable(false);
		categoryRepository.save(category);
		
	}

}
