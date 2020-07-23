package com.brainmatics.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.brainmatics.entity.User;
import com.brainmatics.repositories.UserRepo;

@Service("userService")
@Transactional
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;

	public User save(User user) {
		return userRepo.save(user);
	}

	public Iterable<User> findAll() {
		return userRepo.findAll();
	}

	public User login(String email, String password) {
		User user =  userRepo.findByEmail(email);
		
		if(user==null) {
			return null;
		}
		if(!user.getPassword().equals(password)) {
			return null;
		}
		
		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepo.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}else {
			 List<String> roles = new ArrayList<String>();
	         roles.add("ADMIN");
	         return new User(user,roles);
		}
	}
}
