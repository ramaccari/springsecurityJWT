package com.example.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.models.ERole;
import com.example.models.RoleEntity;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Long> {
	
	public boolean existsByName(ERole name);
	
	public Optional<RoleEntity> findByName(ERole name);

}
