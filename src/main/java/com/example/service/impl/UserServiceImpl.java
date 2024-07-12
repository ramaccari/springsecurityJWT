package com.example.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.models.UserEntity;
import com.example.repositories.UserRepository;
import com.example.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Optional<UserEntity> createUser(UserEntity userEntity) {
		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
		userEntity = userRepository.save(userEntity);
		return Optional.of(userEntity);
	}

	@Override
	public String deleteUser(Long id) {
		userRepository.deleteById(id);
		return "Se ha eliminador el usuario con id: " + id;
	}

}
