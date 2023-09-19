package com.example.demoshopping.model;

import java.lang.annotation.Target;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode.Include;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Good {
  private long id;
  @Include
  private String description;
  @Include
  private double price;
  private static int counter = 0;
  
  public Good(double price, String description){
    this.id = ++counter;
    this.price = price;
    this.description = description;
  }
}
