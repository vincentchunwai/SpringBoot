package com.example.demoresttemplate.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {
  private String name;
  private String catchPhrase;
  private String bs;
}
