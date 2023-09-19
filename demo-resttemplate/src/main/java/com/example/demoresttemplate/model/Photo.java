package com.example.demoresttemplate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class Photo {
  private long albumId;
  private long id;
  private String title;
  private String url;
  private String thumbnailUrl;
}
