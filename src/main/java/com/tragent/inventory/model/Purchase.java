package com.tragent.inventory.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.omg.CORBA.ServerRequest;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "puchase")
public class Purchase {
	
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id", scope=ServerRequest.class)
	@ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
	private Product product;
	
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id", scope=ServerRequest.class)
	@ManyToOne(optional = false)
    @JoinColumn(name = "supplier_id")
	private Supplier supplier;
	
	@Column(nullable=false)
	private int quantity;
	
	@Id
	@Column(nullable=false)
	private Date transactionDate = new Date( );
	
	public Purchase() {
		super();
	}

	public Purchase(Product product, Supplier supplier, int quantity) {
		super();
		this.product = product;
		this.supplier = supplier;
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}	

}
