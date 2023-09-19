package com.example.demoshopping.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration // Annotation on class only
public class AppConfig {
  
  @Bean  // Annotation on method only
   RestTemplate restTemplate(){
    return new RestTemplate();
  }

}
