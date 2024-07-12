package com.example;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.models.ERole;
import com.example.models.RoleEntity;
import com.example.models.UserEntity;
import com.example.service.RoleService;
import com.example.service.UserService;

@SpringBootApplication
public class SpringSecurityJwtApplication {
	
	@Autowired
	UserService userService;
	
	@Autowired
	RoleService roleService;

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJwtApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init() {
		return args -> {
			Optional<RoleEntity> rolAdmin = roleService.createRole(ERole.valueOf(ERole.ADMIN.name()));
			Optional<RoleEntity> rolUser = roleService.createRole(ERole.valueOf(ERole.USER.name()));
			Optional<RoleEntity> rolIvited = roleService.createRole(ERole.valueOf(ERole.INVITED.name()));
			
			UserEntity userAdmin = UserEntity.builder()
					.email("rmaccari@fibertel.com.ar")
					.username("rmaccari")
					.password("1234")
					.roles(Set.of(rolAdmin.get()))
					.build();
			
			userService.createUser(userAdmin);
			
			UserEntity userUser = UserEntity.builder()
					.email("user@fibertel.com.ar")
					.username("user")
					.password("1234")
					.roles(Set.of(rolUser.get()))
					.build();
			
			userService.createUser(userUser);
			
			UserEntity userInvited = UserEntity.builder()
					.email("invited@fibertel.com.ar")
					.username("invited")
					.password("1234")
					.roles(Set.of(rolIvited.get()))
					.build();
			
			userService.createUser(userInvited);
		};
	}

}
