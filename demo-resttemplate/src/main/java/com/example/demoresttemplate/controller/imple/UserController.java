package com.example.demoresttemplate.controller.imple;

import com.example.demoresttemplate.controller.UserOperation;
import com.example.demoresttemplate.framework.ApiResponse;
import com.example.demoresttemplate.framework.BusinessException;
import com.example.demoresttemplate.framework.Code;
import com.example.demoresttemplate.mapper.UserMapper;
import com.example.demoresttemplate.model.Post;
import com.example.demoresttemplate.model.User.User;
import com.example.demoresttemplate.model.User.UserDTO;
import com.example.demoresttemplate.service.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "/api/v2")
public class UserController implements UserOperation {

  @Autowired
  UserService userService;

  @Override
  public ResponseEntity<ApiResponse<List<UserDTO>>> findUser() throws Exception{
    List<User> users = userService.getAllUsers();
    //Conversion logic (from user -> userDTO)
    
    List<UserDTO> userDTO = users.stream().map(u -> UserMapper.map(u)).collect(Collectors.toList());
    ApiResponse<List<UserDTO>> response = ApiResponse.<List<UserDTO>>builder()
        .ok()
        /* .code(Code.OK.getCode())
        .message(Code.OK.getDesc()) */
        .data(userDTO)
        .build();
    return ResponseEntity.ok().body(response);
    
  }

  @Override
  public User getUser(String id) throws Exception{
    try {
      userService.getById(Long.valueOf(id));
    } catch (NumberFormatException num) {
      System.out.println("NumberFormat");
    } catch (NullPointerException nu) {
      System.out.println("NullPointer");
    } catch (IndexOutOfBoundsException idx) {
      System.out.println("IndexOutOfBound");
    }
    return userService.getById(Long.valueOf(id));
  }

  @Override
  public List<Post> getPosts() {
    return userService.getAllPost();
  }
}
