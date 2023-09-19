package com.example.demoshopping.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
//@NoArgsConstructor


public class Order {
  private long id;
  private long customerId;
  private List<Item> items;
  private static long counter = 0;

  @Builder
  public Order(long customerId){
    this.id = ++counter;
    this.customerId = customerId;
    items = new ArrayList<>();
  }
  
  public void addItems(Item item){
    if(Objects.nonNull(item))
    items.add(item);

  }

  public boolean removeItems(Item item){
    boolean successful =false;
    for(Item i: items){
      if(i.equals(item)){
      items.remove(item);
      successful = true;
      break;
      }
    }
    return successful;
  }
  
}
