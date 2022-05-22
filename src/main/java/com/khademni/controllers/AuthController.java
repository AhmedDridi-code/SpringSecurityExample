package com.khademni.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.khademni.dto.JwtRequest;
import com.khademni.dto.JwtResponse;
import com.khademni.models.User;
import com.khademni.repositories.UserRepo;
import com.khademni.services.MyUserDetailsService;
import com.khademni.utils.JWTUtility;

@RestController
public class AuthController {
	@Autowired
	UserRepo userRepo;

	@Autowired
	private JWTUtility jwtUtility;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authmanager;
	
	@GetMapping("/")
	public String home() {
		return"Welcome to JWT Tutorial";
	}
	@CrossOrigin(origins ="http://localhost:4200")
	@PostMapping("/login")
		public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception {
		try {
			authmanager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));

		}catch(BadCredentialsException e) {
			throw new Exception("Invalid Credentials",e);
		}
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
		final String token = jwtUtility.generateToken(userDetails);
		User user = userRepo.findByUsername(jwtRequest.getUsername());
		return new JwtResponse(token,user);
	}
	
}
