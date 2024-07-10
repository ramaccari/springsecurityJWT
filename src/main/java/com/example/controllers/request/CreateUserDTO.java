package com.example.controllers.request;

import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDTO {

	@Email
	@NotBlank
	@Size(max = 80)
	private String email;
	
	@NotBlank
	@Size(max = 30)
	private String name;
	
	@NotBlank
	private String password;
	
	private Set<String> roles;
}
