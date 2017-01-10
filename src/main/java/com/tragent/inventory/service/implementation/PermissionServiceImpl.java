package com.tragent.inventory.service.implementation;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tragent.inventory.model.Permission;
import com.tragent.inventory.repository.PermissionRepository;
import com.tragent.inventory.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {
	
	@Autowired
	PermissionRepository permissionrepository;

	@Override
	public Collection<Permission> findAll() {
		
		Collection<Permission> permission = permissionrepository.findBypermissionEnable(true);
		return permission;
		
	}

	@Override
	public Permission findById(Long id) {
		
		Permission permission = permissionrepository.findOne(id);
		return permission;
	}

	@Override
	public Permission findByName(String name) {
		
		Permission permission = permissionrepository.findByName(name);
		return permission;
	}

	@Override
	public Permission create(Permission permission) {
		
		if(permission.getName() == null){
			//cannot create permission without a name
			return null;
		}
		
		permissionrepository.save(permission);
		return permission;
	}

	@Override
	public Permission update(Permission permission) {
		
		if(permission.getName() == null){
			//cannot update permission without a name
			return null;
		}
		
		permissionrepository.save(permission);
		return permission;
	}

	@Override
	public void delete(Long id) {
		
		Permission permission = permissionrepository.findOne(id);
		if(permission == null){
			return;
		}
		
		permission.setPermissionEnable(false);
		permissionrepository.save(permission);
		
	}
	
}
