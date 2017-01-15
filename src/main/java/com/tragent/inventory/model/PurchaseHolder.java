package com.tragent.inventory.model;

public class PurchaseHolder {

	private Long supplier;
	private Long product;
	private Integer quantity;
	
	public PurchaseHolder(){
		super();
	}

	public PurchaseHolder(Long supplier, Long product, int quantity) {
		super();
		this.supplier = supplier;
		this.product = product;
		this.quantity = quantity;
	}

	public Long getSupplier() {
		return this.supplier;
	}

	public void setSupplier(Long supplier) {
		this.supplier = supplier;
	}

	public Long getProduct() {
		return this.product;
	}

	public void setProduct(Long product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
}