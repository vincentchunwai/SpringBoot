package com.example.demoresttemplate.exception;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demoresttemplate.framework.ApiResponse;
import com.example.demoresttemplate.framework.BusinessException;
import com.example.demoresttemplate.framework.Code;
import com.example.demoresttemplate.model.User.UserDTO;

@RestControllerAdvice // @ResponseBody + @ControllerAdvice
public class GlobalExceptionHandler {
  
  @ExceptionHandler(value = BusinessException.class)
  public ResponseEntity<ApiResponse<Void>> getUserExceptionHandler(){
    
      ApiResponse<Void> response = ApiResponse.<Void>builder()
        .status(Code.JPH_NOTFOUND)
        .data(null)
        .build();
      return ResponseEntity.badRequest().body(response);
    
  }
}
