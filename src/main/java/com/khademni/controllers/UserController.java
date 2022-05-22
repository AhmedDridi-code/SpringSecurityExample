package com.khademni.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.khademni.models.User;
import com.khademni.repositories.UserRepo;


import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
public class UserController {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	UserRepo userRepo;
	@CrossOrigin(origins ="http://localhost:4200")
	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody User user) throws Exception {
		User userFound = userRepo.findByUsername(user.getUsername());
		 
		 log.info("=====================  my logs  ===================================");
		if(userFound!=null) {
			log.error("Username Already Used!!!");
			return ResponseEntity.badRequest().body(null);
		}
		
		log.info("An INFO Message");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepo.save(user);
		return ResponseEntity.ok().body(user);
	}
}
