package com.tragent.inventory.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@SuppressWarnings("serial")
@Entity
@Table(name = "permission")
public class Permission implements GrantedAuthority {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false)
	private String name;
	private String description;
	
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "permission")
	private List<Role> role;
	
	Permission(){
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public String getAuthority() {
		return id.toString();
	}
		
}
