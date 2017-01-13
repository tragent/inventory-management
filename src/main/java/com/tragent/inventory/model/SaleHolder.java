package com.tragent.inventory.model;

public class SaleHolder {
	
	private Long quantity;
	private Long agent;
	private Long product;
	private Long customer;
	
	public SaleHolder(){

	}
	
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public Long getAgent() {
		return agent;
	}
	public void setAgent(Long agent) {
		this.agent = agent;
	}
	public Long getProduct() {
		return product;
	}
	public void setProduct(Long product) {
		this.product = product;
	}
	public Long getCustomer() {
		return customer;
	}
	public void setCustomer(Long customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "SaleHolder [quantity=" + quantity + ", agentId=" + agent + ", productId=" + product
				+ ", customerId=" + customer + "]";
	}
	
}

