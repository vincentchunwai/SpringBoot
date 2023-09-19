package com.example.demoresttemplate.mapper;

import com.example.demoresttemplate.model.User.User;
import com.example.demoresttemplate.model.User.UserDTO;

public class UserMapper {
  
  public static UserDTO map(User user){
    return UserDTO
      .builder()
      .name(user.getName())
      .id(user.getId())
      .username(user.getUsername())
      .build();
  }
}
