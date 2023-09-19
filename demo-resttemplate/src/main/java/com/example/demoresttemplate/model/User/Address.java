package com.example.demoresttemplate.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
  private String street;
  private String suite;
  private String city;
  private String zipcode;
  private Geo geo;
}
