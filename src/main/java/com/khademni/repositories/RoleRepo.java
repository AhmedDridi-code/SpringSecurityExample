package com.khademni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.khademni.models.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
	Role findByName(String name);

}
