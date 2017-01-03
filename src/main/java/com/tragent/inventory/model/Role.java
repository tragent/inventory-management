package com.tragent.inventory.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public enum Role {
	
	SALES_AGENT(1, "Sales agent", "Sales agent at the sales point"),
	ADMIN(1, "Admin", "A Manager or person at higher post able to carry out all possible actions");
	
	@Id
	@GeneratedValue
	private final Integer id;
	
	@Column(nullable=false)
	private final String name;
	private final String description;
	
	@OneToMany()
	private List<User> users;
	
	@OneToMany()
	private List<Permission> permission;
	
	Role(int id, String name, String description){
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
	
	
}
