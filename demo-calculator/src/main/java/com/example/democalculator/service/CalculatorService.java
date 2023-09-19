package com.example.democalculator.service;

import java.util.List;

import org.springframework.stereotype.Repository;


public interface CalculatorService {
  
  Double add(double num1, double num2);

  /**
   * A method to substract num1 by num2
   * @param num1 - A double value
   * @param num2 - A double value
   * @return num1 - num2
   */
  Double minus(double num1, double num2);

  Integer minus(int num1, int num2);

  List<String> getStrings();
}
