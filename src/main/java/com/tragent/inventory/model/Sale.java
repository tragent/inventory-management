package com.tragent.inventory.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.omg.CORBA.ServerRequest;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "sale")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Sale {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private Double totalPrice;

	@Column(nullable = false)
	private Long quantity;
	
	@Column(nullable = false)
	private Date transactionDate = new Date();
	
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id", scope=ServerRequest.class)
	@ManyToOne(optional = false)
	@JoinColumn(name="user_id", referencedColumnName = "id")
	private User user;
	
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id", scope=ServerRequest.class)
	@ManyToOne(optional = false)
	@JoinColumn(name = "product_id", referencedColumnName = "id")
	private Product product;
	
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id", scope=ServerRequest.class)
	@ManyToOne(optional = false)
	@JoinColumn(name = "customer_id",  referencedColumnName = "id")
	private Customer customer;
	
	
	public Sale() {
		
	}

	public Sale(double totalPrice,Long quantity, User user, Product product, Customer customer) {
		this.totalPrice = totalPrice;
		this.quantity = quantity;
		this.user = user;
		this.product = product;
		this.customer = customer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}