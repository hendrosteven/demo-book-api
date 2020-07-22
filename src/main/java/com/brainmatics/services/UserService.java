package com.brainmatics.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brainmatics.entity.Users;
import com.brainmatics.repositories.UserRepo;

@Service("userService")
@Transactional
public class UserService {

	@Autowired
	private UserRepo userRepo;
	
	public Users save(Users user) {
		return userRepo.save(user);
	}
	
	public Iterable<Users> findAll(){
		return userRepo.findAll();
	}
	
	public Users login(String email, String password) {
		Users users = null;
		users = userRepo.findByEmail(email);
		
		if(users==null) {
			return users;
		}
		
		if(users.getPassword().equals(password)) {
			return users;
		}else {
			return null;
		}
	}
}
