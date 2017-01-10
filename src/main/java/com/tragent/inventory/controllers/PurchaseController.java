package com.tragent.inventory.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tragent.inventory.model.Purchase;
import com.tragent.inventory.service.PurchaseService;

@RestController
@RequestMapping("api/v1/purchase")
public class PurchaseController {

	@Autowired
	PurchaseService purchaseService;
	
	/**
	 * Get all purchase transactions or transactions performed on a given date.
	 * 
	 * @return all purchase transaction in the system
	 * @throws ParseException 
	 */
	@RequestMapping(method=RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Purchase>> getPurchaseTransaction(@RequestParam(value = "date", required = false) String date) throws ParseException{
		
		Collection<Purchase> transactions = new ArrayList<Purchase>();
		if (date != null) {
			DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
			Date transactionDate = formatter.parse(date);
			Collection<Purchase> transaction = purchaseService.findByDate(transactionDate);
			transactions.addAll(transaction);
		} else {
			Collection<Purchase> allTransactions = purchaseService.findAll();
			transactions.addAll(allTransactions);
		}
		return new ResponseEntity<Collection<Purchase>>(transactions, HttpStatus.OK);
		
	}
	
	/**
	 * Create new purchase transaction
	 * 
	 * @return the created purchase transaction and HttpStatus.CREATED if purchase was successfully created
	 */
	@RequestMapping(method=RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Purchase> createPurchaseTransaction(@RequestBody Long supplierId, Long productId, Integer quantity){
		
		Purchase transaction = purchaseService.create(supplierId, productId, quantity);
		if (transaction == null) {
			return new ResponseEntity<Purchase>(HttpStatus.FORBIDDEN);
		}
	
		return new ResponseEntity<Purchase>(transaction, HttpStatus.OK);
	}
}
