package com.example.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRolesController {

	@GetMapping("/accessAdmin")
	@PreAuthorize("hasRole('ADMIN')")
	public String accessAdmin() {
		return "Hola, has accedido con rol de ADMIN";
	}

	@GetMapping("/accessUser")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public String accessUser() {
		return "Hola, has accedido con rol de ADMIN o USER";
	}

	@GetMapping("/accessInvited")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER', 'INVITED')")
	public String accessInvited() {
		return "Hola, has accedido con rol de ADMIN, USER o INVITED";
	}
	
}
