package com.tragent.inventory.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false)
	private String name;
	private String description;
	private Double costPrice;
	
	@Column(nullable=false)
	private Long quantity;
	
	@Column(nullable=false)
	private Double unitPrice;
	
	@Column(nullable=false)
	private boolean isActive;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<Purchase> supplier;
	
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "products")
	private List<Category> categories;
	
	@OneToMany(mappedBy="product",  cascade = CascadeType.ALL)
	private List<Sale> customer;
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}
	
}
