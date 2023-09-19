package com.example.demoresttemplate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demoresttemplate.model.User.User;
import com.example.demoresttemplate.service.UserService;

// This is another Testing Environment
// which may not require a full context
@WebMvcTest // AutoConfigureMvc
public class UserControllerTest {
  
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserService userService;

  @Test
  void testGetUsers() throws Exception{
    User user = new User(1L, "John", "John Lau", "john@gmail.com", null, null, null, null);
    User user2 = new User(2L, "Mary", "Mary Lau", "mary@gmail.com", null, null, null, null);

    Mockito.when(userService.getAllUsers()).thenReturn(List.of(user, user2));
    ResultActions mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v2/users"));
  

    mvcResult.andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.data[0].id").value(1))
            .andExpect(jsonPath("$.data[0].name").value("John"))
            .andExpect(jsonPath("$.data[1].id").value(2))
            .andExpect(jsonPath("$.data[1].name").value("Mary"));
            
  }
}
