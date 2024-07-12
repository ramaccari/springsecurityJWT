package com.example.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.models.ERole;
import com.example.models.RoleEntity;
import com.example.repositories.RoleRepository;
import com.example.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Optional<RoleEntity> createRole(ERole eRole) {
		Optional<RoleEntity> opRole = Optional.empty();
		
		RoleEntity roleEntity = RoleEntity.builder().name(ERole.valueOf(eRole.name())).build();
		
		if (!roleRepository.existsByName(roleEntity.getName())) {
			roleRepository.save(roleEntity);
			opRole = Optional.of(roleEntity);
		} else {
			opRole = roleRepository.findByName(roleEntity.getName());
		}
		
		return opRole;
	}

}
