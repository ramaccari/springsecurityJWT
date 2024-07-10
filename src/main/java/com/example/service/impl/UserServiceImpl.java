package com.example.service.impl;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.controllers.request.CreateUserDTO;
import com.example.models.ERole;
import com.example.models.RoleEntity;
import com.example.models.UserEntity;
import com.example.repositories.UserRepository;
import com.example.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserEntity createUser(CreateUserDTO createUserDTO) {
		Set<RoleEntity> roles = createUserDTO.getRoles()
				.stream()
				.map(role -> RoleEntity.builder().name(ERole.valueOf(role)).build())
				.collect(Collectors.toSet());
		
		UserEntity userEntity = UserEntity.builder()
				.email(createUserDTO.getEmail())
				.name(createUserDTO.getName())
				.password(createUserDTO.getPassword())
				.roles(roles)
				.build();
		
		userEntity = userRepository.save(userEntity);
		return userEntity;
	}

	@Override
	public String deleteUser(Long id) {
		userRepository.deleteById(id);
		return "Se ha eliminador el usuario con id: " + id;
	}

}
