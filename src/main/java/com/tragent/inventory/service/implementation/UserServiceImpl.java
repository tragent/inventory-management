package com.tragent.inventory.service.implementation;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tragent.inventory.model.User;
import com.tragent.inventory.repository.UserRepository;
import com.tragent.inventory.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Collection<User> findAll() {
		
		Collection<User> users = userRepository.findByAccountEnabled(true);
		return users;
		
	}

	@Override
	public User findById(Long id) {
		User user = userRepository.findOne(id);
		return user;
		
	}

	@Override
	public User findByUsername(String username) {
		User user = userRepository.findByUsername(username);
		return user;
		
	}

	@Override
	public User findByEmail(String email) {
		
		User user = userRepository.findByEmail(email);
		return user;
		
	}

	@Override
	public User create(User user) {
		
		if (user.getEmail() == null || findByEmail(user.getEmail()) != null) {
			//Cannot create user without email, and email is unique to a user
			return null;
		}
		
		userRepository.save(user);
		return user;
		
	}

	@Override
	public User update(User user) {
		
		User userPersisted = findById(user.getId());
		if (userPersisted == null || user.getEmail() == null ) {
			//Cannot update user that doesn't exist or user without email
			return null;
		}
				
		userRepository.save(user);
		return user;
	}

	@Override
	public void delete(Long id) {
		User user = findById(id);
		if (id == null) {
			return;
		}
		
		user.setAccountEnabled(false);
		userRepository.save(user);
	}
	
}

