package com.hkjava.demo.demofinnhub;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@SpringBootApplication
@ComponentScan("com.hkjava.demo.demofinnhub")
public class DemoFinnhubApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoFinnhubApplication.class, args);

		

	}

}
