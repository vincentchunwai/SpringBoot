package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;

public interface HelloworldController {
  
  @GetMapping(value = "/helloworld")
  String helloworld();
}
