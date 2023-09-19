package com.example.demoshopping.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demoshopping.model.Customer;
import com.example.demoshopping.model.Item;
import com.example.demoshopping.model.Order;

public interface CustomerOperation {
  
  // "/customer -> endpoint"
   @PostMapping(value = "/customer1") // noun, No verb here
  Customer create(@RequestParam String name,
     @RequestParam String email,
     @RequestParam LocalDate dob); 

  @PostMapping(value = "/customer") // noun, No verb here
  Customer createCustomer(@RequestBody Customer customer);

  @GetMapping(value = "/customers")
  List<Customer> printCustomer();

  @GetMapping(value = "/customer/{id}")
  Customer find(@PathVariable(name ="id") String customerId);

  @DeleteMapping(value = "/customer/{id}")
  String remove(@PathVariable(name = "id") String customerId);

  @PutMapping(value = "/customer/{id}") //Put -> table PK
  Customer update(@PathVariable(name = "id") String id, @RequestBody Customer customer);

  @PatchMapping(value = "/customer/id/{id}/email/{email}")
  Customer patchEmail(@PathVariable String email, @PathVariable(name = "id") String id);

  @PatchMapping(value = "/customer/id/{id}/name/{name}")
  Customer patchName(@PathVariable String name, @PathVariable(name = "id") String id);

  @PostMapping(value = "/customer/order/{id}")
  Order addEmptyOrder(@PathVariable(name = "id") String id);

  @PutMapping(value = "/customer/order/{id}")
  Order addItemToOrder(@RequestBody Item item, @PathVariable(name = "id") String id);

  @GetMapping(value="/customer/order")
  List<Order> showOrders();
  
  @PatchMapping(value = "/customer/id/{customerId}/order/{orderId}")
  Customer patchOrder(@PathVariable String customerId, @PathVariable String orderId);

}
