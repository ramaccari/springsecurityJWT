package com.example.service;

import java.util.Optional;

import com.example.models.UserEntity;

public interface UserService {

	public Optional<UserEntity> createUser(UserEntity userEntity);
	
	public String deleteUser(Long id);
}
