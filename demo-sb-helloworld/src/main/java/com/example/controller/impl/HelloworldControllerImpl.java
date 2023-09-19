package com.example.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.Service.HelloworldService;
import com.example.controller.HelloworldController;

@Controller
@ResponseBody
@RequestMapping(value = "/api/v1") // default "/"
public class HelloworldControllerImpl implements HelloworldController{
  
  @Autowired //Check if any object implementing HelloworldService interface
  HelloworldService helloworldService; //stateless variable
  
  @Override
  public String helloworld(){
    return helloworldService.generate(102);
  }

  
}
