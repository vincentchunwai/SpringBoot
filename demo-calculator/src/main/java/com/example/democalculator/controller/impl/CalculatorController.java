package com.example.democalculator.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.democalculator.controller.CalculatorOperation;
import com.example.democalculator.service.CalculatorService;

//@Controller
//@ResponseBody
@RestController // @Controller + @ResponseBody
@RequestMapping(value = "api/v1")
public class CalculatorController implements CalculatorOperation {
  
  @Autowired
  CalculatorService calculatorService;


  @Override
  public List<String> getStrings(){
    for(int i = 0; i < 10; i++){
      calculatorService.getStrings();
    }
    return calculatorService.getStrings();
  }
  
  @Override
  public Double calculate(double x, double y, String z){
    double d = 0;
    try{
      Double.valueOf(z);
    } catch (NumberFormatException e){
      d = 0;
    }
    return calculatorService.add(x, y) + calculatorService.minus(x, d);
  }

  @Override
  public Integer substract(int x, int y, String z){
    int proxy = 0;
    try{
      Integer.valueOf(z);
    } catch (NumberFormatException e){
      proxy = 100;
    }
    return calculatorService.minus(x - y, proxy);
  }
}
