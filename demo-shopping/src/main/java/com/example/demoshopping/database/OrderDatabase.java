package com.example.demoshopping.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demoshopping.model.Customer;
import com.example.demoshopping.model.Item;
import com.example.demoshopping.model.Order;


public class OrderDatabase {
  public static List<Order> orders = new ArrayList<>();

  public static Order Ordering(long id){
    Order order = Order.builder().customerId(id).build();
    orders.add(order);
    return order;
  }

  public static long findCustomerOrder(long id){
    Optional<Order> target = orders.stream().filter(order -> order.getCustomerId() == id).findAny();
    if(target.isPresent()){
      return target.get().getId();
    }
    else{
      return -1;
    }
  }

  /* public static String addItemToOrder(Order order, Item item){
    if(findCustomerOrder(id) != -1){
      orders.stream().filter(order -> order.getId() == id).findFirst().get().addItems(item);
      return "Item has been added to Order";
    }
    return "Item fails to add. Check customer id";
  } */



  
}
