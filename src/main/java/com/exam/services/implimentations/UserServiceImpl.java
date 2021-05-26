package com.exam.services.implimentations;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.entities.User;
import com.exam.entities.UserRole;
import com.exam.repositories.RoleRepository;
import com.exam.repositories.UserRepository;
import com.exam.services.UserService;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	
	//creating user
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {

		User foundUser = this.userRepository.findByUserName(user.getUserName());
		if (foundUser != null) {
			System.out.println("user already there");
			throw new Exception("user alreadt present !!");
		} else {
//		create user
			for (UserRole ur : userRoles) {
				roleRepository.save(ur.getRole());
			}

			user.getUserRoles().addAll(userRoles);
			foundUser = this.userRepository.save(user);
		}
		return foundUser;
	}


	//getting user by userName
	@Override
	public User getUser(String userName) {
		
		return this.userRepository.findByUserName(userName);
	}


	@Override
	public void deleteUser(Long userId) {
		this.userRepository.deleteById(userId);
		
	}

}
