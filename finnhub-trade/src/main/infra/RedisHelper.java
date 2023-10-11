package com.hkjava.demo.demofinnhub.infra;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.client.ResourceAccessException;

import com.hkjava.demo.demofinnhub.exception.FinnhubException;

public class RedisHelper<T> {

  // key value pair, key must be unique, most likely String

  private RedisTemplate<String, T> redisTemplate;

  public RedisHelper(RedisTemplate<String, T> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  public boolean set(String key, T value) {
    try {
      redisTemplate.opsForValue().set(key, value);
      return true;
    } catch (Exception e) {
      throw new ResourceAccessException("Redis Unavailable");
    }
  }
  public boolean set(String key, T value, long time) {
    try {
      redisTemplate.opsForValue().set(key, value, time, TimeUnit.MILLISECONDS);
      return true;
    } catch (Exception e) {
      throw new ResourceAccessException("Redis Unavailable");
    }
  }

  public Object get(String key) {
   try{
      if(Objects.isNull(key))
        return redisTemplate.opsForValue().get(key);
      return null;
   } catch(Exception e){
      throw new ResourceAccessException("Redis unavailable");
   }
  
  }

  public boolean expire(String key, long time){
    try{
      if(time > 0){
      redisTemplate.expire(key, time, TimeUnit.MILLISECONDS);
      return true;
    }
    return false;
  } catch (Exception e){
    throw new ResourceAccessException("Redis unavailable.");
  }
    
  }

}
