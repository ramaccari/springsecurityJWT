package com.example;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.controllers.request.CreateUserDTO;
import com.example.models.ERole;
import com.example.models.RoleEntity;
import com.example.models.UserEntity;
import com.example.service.UserService;

@SpringBootApplication
public class SpringSecurityJwtApplication {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJwtApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init() {
		return args -> {
			UserEntity userAdmin = UserEntity.builder()
					.email("rmaccari@fibertel.com.ar")
					.username("rmaccari")
					.password(passwordEncoder.encode("1234"))
					.roles(Set.of(RoleEntity.builder().name(ERole.valueOf(ERole.ADMIN.name()))
					.build()))
					.build();
			
			Set<String> rolesAdmin = userAdmin.getRoles().stream().map(rol -> rol.getName().name()).collect(Collectors.toSet());
			CreateUserDTO adminDTO = new CreateUserDTO(userAdmin.getEmail(), userAdmin.getUsername(), userAdmin.getPassword(), rolesAdmin);
			userService.createUser(adminDTO);
			
			UserEntity userUser = UserEntity.builder()
					.email("user@fibertel.com.ar")
					.username("user")
					.password(passwordEncoder.encode("1234"))
					.roles(Set.of(RoleEntity.builder().name(ERole.valueOf(ERole.USER.name()))
					.build()))
					.build();
			
			Set<String> rolesUser = userUser.getRoles().stream().map(rol -> rol.getName().name()).collect(Collectors.toSet());
			CreateUserDTO userDTO = new CreateUserDTO(userUser.getEmail(), userUser.getUsername(), userUser.getPassword(), rolesUser);
			userService.createUser(userDTO);
			
			UserEntity userInvite = UserEntity.builder()
					.email("invite@fibertel.com.ar")
					.username("invite")
					.password(passwordEncoder.encode("1234"))
					.roles(Set.of(RoleEntity.builder().name(ERole.valueOf(ERole.INVITE.name()))
					.build()))
					.build();
			
			Set<String> rolesInvite = userInvite.getRoles().stream().map(rol -> rol.getName().name()).collect(Collectors.toSet());
			CreateUserDTO inviteDTO = new CreateUserDTO(userInvite.getEmail(), userInvite.getUsername(), userInvite.getPassword(), rolesInvite);
			userService.createUser(inviteDTO);
		};
	}

}
