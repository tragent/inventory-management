package com.tragent.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tragent.inventory.model.User;


/**
 * Repository used by UserService to access database.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername(@Param("username") String username);

    User findByEmail(@Param("email") String email);
    
}
