package com.example.democalculator.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.example.democalculator.service.CalculatorService;

@Service
public class CalculatorServiceImpl implements CalculatorService{
  
  @Autowired
  @Qualifier(value = "arrayList")
  List<String> strings;
  
  @Override
  public List<String> getStrings(){
    strings.add("abc");
    strings.add("ijk");
    strings.add("xyz");
    return strings;
  }
  @Override
  public Double add(double num1, double num2){
    return num1 + num2;
  }

  @Override
  public Double minus(double num1, double num2){
    return num1 - num2;
  }

  @Override
  public Integer minus(int num1, int num2){
    return num1 - num2;
  }
}
