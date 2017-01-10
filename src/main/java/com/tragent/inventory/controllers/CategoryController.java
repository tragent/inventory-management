package com.tragent.inventory.controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tragent.inventory.model.Category;
import com.tragent.inventory.service.CategoryService;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	/**
	 * Get all categories.
	 * 
	 * @return all product categories in the system
	 */
	@RequestMapping(method=RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Category>> getCategories(@RequestParam(value = "name", required = false) String name){
		
		Collection<Category> categories = new ArrayList<Category>();
		if (name != null) {
			Category category = categoryService.findByName(name);
			categories.add(category);
		} else {
			Collection<Category> allCategories = categoryService.findAll();
			categories.addAll(allCategories);
		}
		return new ResponseEntity<Collection<Category>>(categories, HttpStatus.OK);
		
	}
	
	/**
	 * Get category with given category id.
	 * 
	 * @param id category's id
	 * @return the category with given id or 404 if id is not found
	 */
	@RequestMapping(value="/{id}",
			method=RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Category> getCategoryById(@PathVariable("id") Long id){
		
		Category category = categoryService.findById(id);
		if (category == null) {
			return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Category>(category, HttpStatus.OK);
		
	}
	
	/**
	 * Create new category
	 * 
	 * @param category
	 * @return the created category and HttpStatus.CREATED if category was successfully created
	 */
	@RequestMapping(method=RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Category> createCategory(@RequestBody Category category){
		
		category = categoryService.create(category);
		if (category == null) {
			return new ResponseEntity<Category>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<Category>(category, HttpStatus.CREATED);
		
	}
	
	/**
	 * update category's information
	 * 
	 * @param id, category's id
	 * @return the updated category's information.
	 */
	@RequestMapping(value="/{id}",
			method=RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Category> updateCategory(@RequestBody Category category){
		
		category = categoryService.update(category);
		if (category == null) {
			return new ResponseEntity<Category>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<Category>(category, HttpStatus.OK);
		
	}
	
	/**
	 * Delete category's information
	 * 
	 * @param id, category's id
	 * @return 204, .
	 */
	@RequestMapping(value="/{id}",
			method=RequestMethod.DELETE)
	public ResponseEntity<Category> deleteCategory(@PathVariable("id") Long id){
		
		categoryService.delete(id);
		return new ResponseEntity<Category>(HttpStatus.NO_CONTENT);
		
	}

}
