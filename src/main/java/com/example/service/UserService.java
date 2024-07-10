package com.example.service;

import com.example.controllers.request.CreateUserDTO;
import com.example.models.UserEntity;

public interface UserService {

	public UserEntity createUser(CreateUserDTO createUserDTO);
	
	public String deleteUser(Long id);
}
