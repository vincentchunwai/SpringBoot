package com.example.demoresttemplate.service;

import java.util.ArrayList;
import java.util.List;

import com.example.demoresttemplate.framework.BusinessException;
import com.example.demoresttemplate.model.Post;
import com.example.demoresttemplate.model.User.User;


public interface UserService {
  
  List<User> getAllUsers() throws BusinessException;

  User getById(Long id) throws BusinessException;

  List<Post> getAllPost();
}
