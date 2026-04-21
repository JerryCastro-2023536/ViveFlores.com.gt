package com.viveflores.blogturistico;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PrincipalApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PrincipalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Test API");
	}
}
