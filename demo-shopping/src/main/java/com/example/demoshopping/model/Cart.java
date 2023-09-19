package com.example.demoshopping.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
public class Cart {
  private long id;
  private Customer customer; // customer id
  private Order order;
  private static long counter = 0;

  @Builder
  public Cart(Customer customer, Order order){
    this.id = ++counter;
    this.customer = customer;
    this.order = order;
  }

  public void add(Item item){
    this.order.addItems(item);
  }

  public void remove(Item item){
    this.order.removeItems(item);
  }
}
