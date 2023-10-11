package com.hkjava.demo.demofinnhub.infra;

import java.util.List;

import com.hkjava.demo.demofinnhub.model.StockSymbol;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Custom Api Response Standard DTO")
public class ApiResponse<T> {
  // attribute name by default same as JSON field name after serialziation
  @ApiModelProperty(value = "Code for System Response Category", required = true)
  private int code;
  @ApiModelProperty(value = "Message to indicate error")
  private String message;
  @ApiModelProperty(value = "Response Data Body")
  private T data;

  public int getCode() {
    return this.code;
  }

  public String getMessage() {
    return this.message;
  }

  public T getData() {
    return this.data;
  }

  public static <T> ApiResponseBuilder<T> builder() {
    return new ApiResponseBuilder<>();
  }

  private ApiResponse(ApiResponseBuilder<T> builder) {
    this.code = builder.code;
    this.message = builder.message;
    this.data = builder.data;
  }

  public static class ApiResponseBuilder<T> {
    private int code;
    private String message;
    private T data;

    public ApiResponseBuilder<T> status(Code code) {
      this.code = code.getCode();
      this.message = code.getDesc();
      return this;
    }

    public ApiResponseBuilder<T> concatMessageIfPresent(String str) {
      if (this.message != null && str != null)
        this.message += " " + str;
      return this;
    }

    public ApiResponseBuilder<T> ok() {
      this.code = Code.OK.getCode();
      this.message = Code.OK.getDesc();
      return this;
    }

    public ApiResponseBuilder<T> data(T list) {
      this.data = list;
      return this;
    }

    public ApiResponse<T> build() {
      if (this.code == 0 || this.message == null)
        throw new RuntimeException();
      return new ApiResponse<>(this);
    }

  }
}

// {
// "code" : 200,
// "message" : "OK",
// "data" : [

// ],
// "error" : [
// "", ""
// ],
// }
