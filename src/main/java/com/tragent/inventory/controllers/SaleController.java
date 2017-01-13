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
import org.springframework.web.bind.annotation.RestController;

import com.tragent.inventory.model.Sale;
import com.tragent.inventory.model.SaleHolder;
import com.tragent.inventory.service.SaleService;

@RestController
@RequestMapping("api/v1/sales")
public class SaleController {
	
	@Autowired
	private  SaleService saleService;
	
	/**
	 * Get all sales transactions or transactions performed on a given date.
	 * 
	 * @return all sales transaction in the system
	  
	 */
	@RequestMapping(method=RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Sale>> getSaleTransactions() {
		
		Collection<Sale> transactions = new ArrayList<Sale>();
		
			Collection<Sale> allTransactions = saleService.findAll();
			transactions.addAll(allTransactions);
		return new ResponseEntity<Collection<Sale>>(transactions, HttpStatus.OK);
		
	}
	
	/**
	 * Get sale with given sale id.
	 * 
	 * @param id id of the customer to return
	 * @return the sale with given id or 404 if id is not found
	 */
	@RequestMapping(
			value = "/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Sale> getSaleById(@PathVariable("id") Long id){
		Sale sale = saleService.findById(id);
		if (sale == null) {
			return new ResponseEntity<Sale>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Sale>(sale, HttpStatus.OK);
	}
	
	/**
	 * Create new sale
	 * 
	 * @param quantity
	 * @param agentId
	 * @param productId
	 * @param customerId
	 * @return the created sale and HttpStatus.CREATED if sale was successfully created
	 */
	@RequestMapping(method=RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Sale> createSale(@RequestBody SaleHolder saleHolder){
		
		Sale saleTransaction = saleService.create(saleHolder.getQuantity(),saleHolder.getAgent(), saleHolder.getProduct(), saleHolder.getCustomer());
		if (saleTransaction == null) {
			return new ResponseEntity<Sale>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<Sale>(saleTransaction, HttpStatus.CREATED);
		
	}
	
}	
	
