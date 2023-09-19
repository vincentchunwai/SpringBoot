package com.example.democalculator.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface CalculatorOperation {
  
  @GetMapping(value = "/calculate")
   Double calculate(@RequestParam(name = "x") double salary, 
    @RequestParam(name = "y") double bonus,
    @RequestParam(name = "z", required = false, defaultValue = "100") String dividend);


  @GetMapping(value = {"/substract/{x}/{y}/{z}", "/substract/{x}/{y}"})
  Integer substract(@PathVariable(name = "x") int salary, 
  @PathVariable int y, @PathVariable(required = false) String z);
  
  @GetMapping(value = "/strings")
  List<String> getStrings();
}
