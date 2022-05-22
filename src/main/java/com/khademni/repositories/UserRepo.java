package com.khademni.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.khademni.models.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
