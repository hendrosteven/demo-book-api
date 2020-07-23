package com.brainmatics.repositories;

import org.springframework.data.repository.CrudRepository;

import com.brainmatics.entity.User;

public interface UserRepo extends CrudRepository<User, Long> {

	public User findByEmail(String email);
}
