package com.exam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	//this default implimention given by spring  
	public User findByUserName(String userName);

}
