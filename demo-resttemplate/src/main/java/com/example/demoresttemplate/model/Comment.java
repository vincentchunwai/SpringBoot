package com.example.demoresttemplate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class Comment {
  private long postId;
  private long id;
  private String name;
  private String email;
  private String body;
}
