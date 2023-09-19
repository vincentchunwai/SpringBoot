package com.example.demoresttemplate.model.User;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonSerialize
public class UserDTO {
  private Long id;
  private String name;
  @JsonProperty(value = "New Name")
  private String username;

}
