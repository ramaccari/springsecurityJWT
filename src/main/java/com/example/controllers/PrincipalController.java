package com.example.controllers;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.controllers.request.CreateUserDTO;
import com.example.models.ERole;
import com.example.models.RoleEntity;
import com.example.models.UserEntity;
import com.example.service.RoleService;
import com.example.service.UserService;

import jakarta.validation.Valid;

@RestController
public class PrincipalController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping("/Hello")
	public String hello() {
		return "Hello World ¡NOT secured!";
	}

	@GetMapping("/HelloSecured")
	public String helloSecured() {
		return "Hello World ¡secured!";
	}

	@PostMapping("/createUser")
	public ResponseEntity<UserEntity> createUser(@Valid @RequestBody CreateUserDTO createUserDTO) {
		UserEntity userEntity = UserEntity.builder()
				.email(createUserDTO.getEmail())
				.password(createUserDTO.getPassword())
				.username(createUserDTO.getUsername())
				.build();
		
		Set<RoleEntity> roles = createUserDTO.getRoles().stream()
				.map(str -> roleService.createRole(ERole.valueOf(str)).get())
				.collect(Collectors.toSet());
		userEntity.setRoles(roles);
		
		userService.createUser(userEntity);
		return ResponseEntity.ok(userEntity);
	}
	
	@DeleteMapping("/deleteUser")
	public String deleteUser(@RequestParam Long id) {
		String reponse = userService.deleteUser(id);
		return reponse;
	}

}
