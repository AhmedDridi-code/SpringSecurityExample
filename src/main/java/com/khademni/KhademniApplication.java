package com.khademni;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.khademni.models.Role;
import com.khademni.models.User;
@SpringBootApplication
public class KhademniApplication {

	public static void main(String[] args) {
		SpringApplication.run(KhademniApplication.class, args);
	}
	

}
