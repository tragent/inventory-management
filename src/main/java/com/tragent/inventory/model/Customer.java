package com.tragent.inventory.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {
	
	@Id
	@GeneratedValue
	Long id;

	@Column(nullable=false)
	private String firstName;
	
	@Column(nullable=false)
	private String lastName;
	
	@Column(nullable=true)
	private String email;
	
	@Column(nullable=false)
	private String phoneNumber;
	
	@Column(nullable=false)
	private boolean isActive;
	
	@OneToMany(mappedBy="customer", cascade = CascadeType.ALL)
	private List<Sale> product;
	
	public Customer() {
		
	}

	public Long getId() {
		return id;
	}

	/*public void setId(BigInteger id) {
		this.id = id;
	}*/

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
}
