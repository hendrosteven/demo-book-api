package com.brainmatics.repositories;

import org.springframework.data.repository.CrudRepository;

import com.brainmatics.entity.Users;

public interface UserRepo extends CrudRepository<Users, Long> {

	public Users findByEmail(String email);
}
