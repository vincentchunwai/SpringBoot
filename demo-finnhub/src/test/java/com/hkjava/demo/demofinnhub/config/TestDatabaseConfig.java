package com.hkjava.demo.demofinnhub.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ActiveProfiles;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.sql.DataSource;

@TestConfiguration
public class TestDatabaseConfig {
  
  @Bean
  @Primary
  @ConfigurationProperties(prefix = "spring.datasource")
  DataSource dataSource(){
    return DataSourceBuilder.create()
      .url("jdbc:h2:mem:testdb")
      .driverClassName("org.h2.Driver")
      .build();
  }

  @Bean
  ObjectMapper objectMapper(){
    return new ObjectMapper();
  }
}
