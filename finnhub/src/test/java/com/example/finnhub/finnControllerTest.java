package com.example.finnhub;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.finnhub.model.Combine;
import com.example.finnhub.model.QuoteDTO;
import com.example.finnhub.model.Stock;
import com.example.finnhub.service.finnService;

@WebMvcTest
public class finnControllerTest {
  
   @Autowired
  private MockMvc mockMvc;

  @MockBean
  private finnService finnService;

  

  @Test
  void testShowQuote() throws Exception{
    
    QuoteDTO mockQuoteDTO = new QuoteDTO(177L, 289L, 80L, 1212L, 322L, 1111L, 172L);
    Stock mockStock = new Stock("USA", "USD", "USD", null, null, null, null, null, null, null, null, null, null);
    
    Mockito.when(finnService.showQuote("AAPL")).thenReturn(mockQuoteDTO);
    Mockito.when(finnService.showStock("AAPL")).thenReturn(mockStock);
   
    
    
    
    ResultActions mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/general/AAPL"));

    mvcResult.andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.data.quote.Current Price").value(177L))
      .andExpect(jsonPath("$.data.quote.Previous close price").value(172L))
      .andExpect(jsonPath("$.data.stock.currency").value("USD"));
  } 
}
