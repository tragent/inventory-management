package com.tragent.inventory.service.implementation;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tragent.inventory.model.Permission;
import com.tragent.inventory.model.Role;
import com.tragent.inventory.repository.RoleRepository;
import com.tragent.inventory.service.PermissionService;
import com.tragent.inventory.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PermissionService permissionService;

	@Override
	public Collection<Role> findAll() {
		
		Collection<Role> roles = roleRepository.findByroleEnable(true);
		return roles;
		
	}

	@Override
	public Role findById(int id) {
		
		Role role = roleRepository.findByid(id);
		return role;
		
	}

	@Override
	public Role findByName(String name) {
		
		Role role = roleRepository.findByName(name);
		return role;
	}

	@Override
	public Role create(Role role) {
		
		if (role.getName() == null) {
			//cannot create role without a name
			return null;
		}
		
		roleRepository.save(role);
		return role;
	}

	@Override
	public Role update(Role role) {
		
		if (role.getName() == null) {
			//cannot update role without a category name
			return null;
		}
		
		roleRepository.save(role);
		return role;
		
	}

	@Override
	public void delete(int id) {
		
		Role role = roleRepository.findByid(id);
		if (role == null) {
			return;
		}
		
		role.setRoleEnable(false);
		roleRepository.save(role);
		
	}

	@Override
	public Collection<Permission> updatePermission(int roleId, Long permissionId) {
		
		Role role = roleRepository.findByid(roleId);
		Permission newPermission = permissionService.findById(permissionId);
		if (role == null || newPermission == null ) {
			return null;
		}
				
		List<Permission> permissions = role.getPermission();
		permissions.add(newPermission);
		role.setPermission(permissions);
		roleRepository.save(role);
		return role.getPermission();
	}
}
