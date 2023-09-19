package com.example.demoresttemplate.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demoresttemplate.framework.ApiResponse;
import com.example.demoresttemplate.model.Post;
import com.example.demoresttemplate.model.User.User;
import com.example.demoresttemplate.model.User.UserDTO;

public interface UserOperation {

  @GetMapping(value = "/users")
  ResponseEntity<ApiResponse<List<UserDTO>>> findUser() throws Exception;

  @GetMapping(value = "/users/{id}")
  User getUser(@PathVariable String id) throws Exception;

  @PostMapping(value = "/posts")
  List<Post> getPosts();
}
