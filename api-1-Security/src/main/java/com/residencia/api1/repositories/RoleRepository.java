package com.residencia.api1.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.residencia.api1.entities.Role;
import com.residencia.api1.entities.RoleEnum;
import com.residencia.api1.repositories.RoleRepository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	Optional<Role> findByName(RoleEnum name);
}