package com.example.demoresttemplate.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class Post {
  private long userId;
  private long id;
  private String title;
  private String body;
  
}
