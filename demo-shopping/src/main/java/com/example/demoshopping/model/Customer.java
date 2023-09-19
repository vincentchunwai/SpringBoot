package com.example.demoshopping.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.EqualsAndHashCode.Include;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Customer {
  @Include
  private long id;
  private String name;
  private String email;
  private LocalDate dob;
  private List<Order> orders;
  private static long counter = 0;

  @Builder
  public Customer(String name, String email, LocalDate dob){
    this.id = ++counter;
    this.name = name;
    this.email = email;
    this.dob = dob;
    orders = new ArrayList<>();
  }

  
 
}
