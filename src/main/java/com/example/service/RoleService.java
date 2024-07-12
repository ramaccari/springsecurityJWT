package com.example.service;

import java.util.Optional;

import com.example.models.ERole;
import com.example.models.RoleEntity;

public interface RoleService {
	
	public Optional<RoleEntity> createRole(ERole eRole);

}
