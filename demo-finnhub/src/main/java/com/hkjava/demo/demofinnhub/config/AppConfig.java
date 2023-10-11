package com.hkjava.demo.demofinnhub.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hkjava.demo.demofinnhub.infra.RedisHelper;
import com.hkjava.demo.demofinnhub.infra.UnixTimeHelper;
import com.hkjava.demo.demofinnhub.model.apiModel.CompanyProfile;

@Configuration
public class AppConfig {

  @Value(value = "${api.finnhub.token}")
  private String token;

  @Bean
  ModelMapper modelMapper() {
    return new ModelMapper();
  }

  @Bean
   RestTemplate restTemplate() {
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

    return restTemplate;
}

  @Bean
  UnixTimeHelper utilities() {
    return new UnixTimeHelper();
  }

  @Bean
  ObjectMapper objectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule()); // Register the JavaTimeModule
    return objectMapper;
  }

  @Bean
  @Qualifier("finnhubToken")
  String finnhubToken() {
    return token;
  }

  @Bean
    RedisTemplate<String, CompanyProfile> redisTemplate(RedisConnectionFactory redisConnectionFactory,
    ObjectMapper objectMapper){
    RedisTemplate<String, CompanyProfile> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(redisConnectionFactory);
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(CompanyProfile.class));
    
    ((Jackson2JsonRedisSerializer<?>) redisTemplate.getValueSerializer()).setObjectMapper(objectMapper);
    return redisTemplate;
  }

  @Bean
  RedisHelper<CompanyProfile> redisProfileHelper(RedisTemplate<String, CompanyProfile> redisTemplate){
    return new RedisHelper<>(redisTemplate);
  }

}
