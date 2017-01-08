package com.tragent.inventory.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@SuppressWarnings("serial")
@Entity
<<<<<<< HEAD
public enum Role {
	
	SALES_AGENT(1, "Sales agent", "Sales agent at the sales point"),
	ADMIN(1, "Admin", "A Manager or person at higher post able to carry out all possible actions");
=======
@Table(name = "role")
public class Role implements GrantedAuthority {
>>>>>>> 144784aedeaf1e74407117e3b97b45f75a480766
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(nullable=false)
	private String name;
	private String description;
	
	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
	private List<User> users;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="role_permission", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "permission_id"))
	private List<Permission> permission;
	
	Role(int id, String name, String description){
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	public Role() {
		super();
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
<<<<<<< HEAD
	
	
=======

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Permission> getPermission() {
		return permission;
	}

	public void setPermission(List<Permission> permission) {
		this.permission = permission;
	}

	@Override
	public String getAuthority() {
		return id.toString();
	}
		
>>>>>>> 144784aedeaf1e74407117e3b97b45f75a480766
}
