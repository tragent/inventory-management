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

import com.tragent.inventory.model.Product;
import com.tragent.inventory.service.ProductService;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	/**
	 * Get all product.
	 * 
	 * @return all product  in the system or with a particular name
	 */
	@RequestMapping(method=RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Product>> getProduct(@RequestParam(value = "name", required = false) String name){
		
		Collection<Product> products = new ArrayList<Product>();
		if (name != null) {
			Product product = productService.findByName(name);
			products.add(product);
		} else {
			Collection<Product> allProducts = productService.findAll();
			products.addAll(allProducts);
		}
		return new ResponseEntity<Collection<Product>>(products, HttpStatus.OK);
		
	}
	
	/**
	 * Get product with given product id.
	 * 
	 * @param id product's id
	 * @return the product with given id or 404 if id is not found
	 */
	@RequestMapping(value="/{id}",
			method=RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> getProductById(@PathVariable("id") Long id){
		
		Product product= productService.findById(id);
		if (product == null) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Product>(product, HttpStatus.OK);
		
	}
	
	/**
	 * Create new product
	 * 
	 * @param product
	 * @return the created product and HttpStatus.CREATED if product was successfully created
	 */
	@RequestMapping(method=RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> createProduct(@RequestBody Product product){
		
		product = productService.create(product);
		if (product == null) {
			return new ResponseEntity<Product>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<Product>(product, HttpStatus.CREATED);
		
	}
	
	/**
	 * update  product
	 * 
	 * @param id, product's id
	 * @return the updated product
	 */
	@RequestMapping(value="/{id}",
			method=RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> updateProduct(@RequestBody Product product){
		
		product = productService.update(product);
		if (product == null) {
			return new ResponseEntity<Product>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<Product>(product, HttpStatus.OK);
		
	}
	
	/**
	 * Activate or deactivate a product
	 * 
	 * @param id, product's id
	 * @return 204, .
	 */
	@RequestMapping(value="/{id}",
			method=RequestMethod.DELETE)
	public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id){
		
		productService.delete(id);
		return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
		
	}

}

