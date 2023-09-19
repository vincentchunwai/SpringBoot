package com.example.demoshopping.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.example.demoshopping.model.Cart;

public class CartDatabase {
  public static List<Cart> carts= new ArrayList<>();

  /**
   * remove cart by customer id from List<Cart>
   * @param customerId
   */
  public static void clear(int customerId){
    Cart target = carts.stream().filter(cart -> cart.getCustomer().getId() == customerId).findAny().orElse(null);
    if(Objects.nonNull(target)){
      carts.remove(target);
    }
  }

  public static void add(Cart cart){
    carts.add(cart);
  }
}
