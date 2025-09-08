package com.smarttaskpro.smarttask;

import com.smarttaskpro.smarttask.model.User;
import com.smarttaskpro.smarttask.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SmarttaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmarttaskApplication.class, args);
	}
//	@Bean
//	public CommandLineRunner testUserRepository(UserRepository userRepository) {
//		return args -> {
//			User user = new User(null, "Alice", "alice@example.com", "password123");
//			userRepository.save(user);
//
//			System.out.println("All users in the database:");
//			userRepository.findAll().forEach(System.out::println);
//		};
//	}
}
