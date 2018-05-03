package com.sl.rbcweather.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sl.rbcweather.model.User;

/**
 * @author Santiago Leiva
 * 02/05/2018
 */

public interface UserRepository extends JpaRepository<User, Long> {
	
	@SuppressWarnings("unchecked")
	User save(User user);
	
	User findByUsername(String username);

}
