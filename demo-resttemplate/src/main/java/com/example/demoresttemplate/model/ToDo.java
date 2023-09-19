package com.example.demoresttemplate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class ToDo {
  private long userId;
  private long id;
  private String title;
  private boolean completed;
}
