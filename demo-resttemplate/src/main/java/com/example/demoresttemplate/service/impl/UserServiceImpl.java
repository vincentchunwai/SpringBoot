package com.example.demoresttemplate.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.UriComponentsBuilderMethodArgumentResolver;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demoresttemplate.framework.BusinessException;
import com.example.demoresttemplate.framework.Code;
import com.example.demoresttemplate.framework.Protocol;
import com.example.demoresttemplate.model.Post;
import com.example.demoresttemplate.model.User.User;
import com.example.demoresttemplate.service.UserService;

// Invoke API + Deserialization (JSON -> Object)

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private RestTemplate restTemplate;

  @Value(value = "${api.jsonplaceholder.domain}")
  private String jphDomain;

  @Value(value = "${api.jsonplaceholder.endpoints.user}")
  private String userEndpoint;

  @Value(value = "${api.jsonplaceholder.endpoints.post}")
  private String postEndpoint;

  @Override
  public List<User> getAllUsers() throws BusinessException {
    String url = UriComponentsBuilder.newInstance()
        .scheme(Protocol.HTTP.name())
        .host(jphDomain)
        .path(userEndpoint)
        .toUriString();

    System.out.println("url=" + url);
    try{
      User[] users = restTemplate.getForObject(url, User[].class);
      return Arrays.asList(users);
      
    } catch (RestClientException e){
      throw new BusinessException(Code.JPH_NOTFOUND);
    }


    
      /* ResponseEntity<List<User>> response = restTemplate.exchange(
      url,
      HttpMethod.GET,
      null,
      new ParameterizedTypeReference<List<User>>() {
      });
      
      if (response.getStatusCode() == HttpStatus.OK) {
      return response.getBody();
      } else {
      throw new RuntimeException("Failed to fetch users: " +
      response.getStatusCode());
      } */
    
  }

  @Override
  public User getById(Long id) throws BusinessException{

    return getAllUsers().stream().filter(c -> c.getId() == id).findFirst().get();
  }

  @Override
  public List<Post> getAllPost(){

    String url = UriComponentsBuilder.newInstance()
        .scheme("https")
        .host(jphDomain)
        .path(postEndpoint)
        .toUriString();
    System.out.println("url=" + url);
    Post[] posts = restTemplate.getForObject(url, Post[].class);
    return Arrays.asList(posts);
  }

}
