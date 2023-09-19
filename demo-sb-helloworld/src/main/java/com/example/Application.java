package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@Configuration
// EnableAutoConfiguration
// ComponentScan (Most important)
// ComponentScan is to scan @Controller, @Service, @Repository, @Configuration

//if yes, create an object of those classes and put that object into SpringContext
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
