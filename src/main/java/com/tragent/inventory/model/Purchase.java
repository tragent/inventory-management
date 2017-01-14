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
	
	@Id
	private String id;
	
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id", scope=ServerRequest.class)
	@ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
	private Product product;
	
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id", scope=ServerRequest.class)
	@ManyToOne(optional = false)
    @JoinColumn(name = "supplier_id")
	private Supplier supplier;
	
	@Column(nullable=false)
	private Integer quantity;
	
	@Column(nullable=false)
	private Date transactionDate = new Date( );
	
	@Column(nullable=false)
	private String status;
	
	public Purchase() {
		super();
	}

	public Purchase(Product product, Supplier supplier, Integer quantity) {
		super();
		this.id = "TRN"+transactionDate.getTime();
		this.product = product;
		this.supplier = supplier;
		this.quantity = quantity;
		this.status = "Pending";
	}
	
	public String getId() {
		return this.id;
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

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}