package com.example.Service.impl;

import org.springframework.stereotype.Service;

import com.example.Service.HelloworldService;

@Service
public class HelloworldServiceImpl implements HelloworldService{
  
  @Override
  public String generate(int x){
    if(x > 100)
      return "hello world";
    
    return "ABC";
  }
}
