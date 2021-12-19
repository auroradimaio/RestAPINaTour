package com.example.NaTour21;

import com.example.NaTour21.User.Entity.User;
import com.example.NaTour21.User.Service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication

public class NaTour21Application {

	public static void main(String[] args) {
		SpringApplication.run(NaTour21Application.class, args);
	}

	@Bean
	BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

}
