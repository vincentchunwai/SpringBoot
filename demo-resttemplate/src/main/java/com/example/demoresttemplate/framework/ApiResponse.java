package com.example.demoresttemplate.framework;

import javax.management.RuntimeErrorException;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
public class ApiResponse<T> {
  @JsonProperty("number")
  private int code;
  private String message;
  private T data; // attribute name by default same as field name after serialization

  public int getCode(){
    return this.code;
  }

  public String getMessage(){
    return this.message;
  }

  public T getData(){
    return this.data;
  }

  private ApiResponse(ApiResonseBuilder<T> builder){
    this.code = builder.code;
    this.message = builder.message;
    this.data = builder.data;
  }

  public static <T> ApiResonseBuilder<T> builder(){
    return new ApiResonseBuilder<>();
  }

  public static class ApiResonseBuilder<T>{
    private int code;
    private String message;
    private T data;
    private ok ok = new ok();

    
     public ApiResonseBuilder<T> status(Code code){
      this.code = code.getCode();
      this.message = code.getDesc();
      return this;
    }

    public ApiResonseBuilder<T> ok(){
      this.code = ok.getCode();
      this.message = ok.getDesc();
      return this;
    }

    public ApiResonseBuilder<T> data(T data){
      this.data = data;
      return this;
    }

    public ApiResponse<T> build(){
      if(this.code == 0 || this.message == null)
        throw new RuntimeException();
      return new ApiResponse<>(this);
    }
  }
}
/*
 * {
 * "code" : 200
 * "message" : "OK",
 * "data" : [
 * 
 * ],
 * "error" : [
 * 
 * ]
 * }
 */