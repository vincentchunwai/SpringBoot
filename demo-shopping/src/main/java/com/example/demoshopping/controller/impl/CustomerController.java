package com.example.demoshopping.controller.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoshopping.controller.CustomerOperation;
import com.example.demoshopping.database.CustomerDatabase;
import com.example.demoshopping.database.OrderDatabase;
import com.example.demoshopping.model.Customer;
import com.example.demoshopping.model.Item;
import com.example.demoshopping.model.Order;
import com.example.demoshopping.service.CustomerService;

@RestController
@RequestMapping(value = "/api/v1")
public class CustomerController implements CustomerOperation{
  @Autowired
  CustomerService customerService;

  @Override
  public Customer createCustomer(Customer customer){
    Customer newCustomer = customerService.create(customer);
    try{
       customerService.newOrder(newCustomer.getId());
      } catch(Exception ex){
        
      }
    
     customerService.newOrder(newCustomer.getId());
    return newCustomer;
  }

  @Override
  public List<Order> showOrders(){
    return customerService.showOrders();
  }

  @Override
  public Order addEmptyOrder(String customerId){
    if(!customerId.isBlank() && !Objects.nonNull(customerId)){
      try{
        return customerService.newOrder(Long.valueOf(customerId));
      } catch(Exception ex){
        
      }
    }
    return customerService.newOrder(Long.valueOf(customerId));
  }

  @Override
  public Customer patchOrder(String customerId, String orderId){
    if(customerId == orderId && Objects.nonNull(customerId) && Objects.nonNull(orderId)
    && customerService.findCustomerOrder(Long.valueOf(customerId)) != -1){
      try{
        Order order = OrderDatabase.orders.stream().filter(o -> o.getCustomerId() == ((long)Long.valueOf(orderId))).findFirst().get();
        CustomerDatabase.customers.stream().filter(c -> c.getId() == ((long)Long.valueOf(customerId))).findFirst().get().getOrders().add(order);
      } catch (Exception ex){

      }
    }
    Order order = OrderDatabase.orders.stream().filter(o -> o.getCustomerId() == ((long)Long.valueOf(orderId))).findFirst().get();
    CustomerDatabase.customers.stream().filter(c -> c.getId() == ((long)Long.valueOf(customerId))).findFirst().get().getOrders().add(order);
    return CustomerDatabase.customers.stream().filter(c -> c.getId() == Long.valueOf(customerId)).findFirst().get();
  }
  @Override
  public Order addItemToOrder(@RequestBody Item item, String customerId){
    if(!customerId.isBlank() && !Objects.nonNull(customerId) && customerService.findCustomerOrder(Long.valueOf(customerId)) != -1){
      try{
        OrderDatabase.orders.stream().filter(o -> o.getCustomerId() == Long.valueOf(customerId)).findFirst().get().addItems(item);
        
      } catch (Exception ex){

      }
    }
    OrderDatabase.orders.stream().filter(o -> o.getCustomerId() == Long.valueOf(customerId)).findFirst().get().addItems(item);
    return OrderDatabase.orders.stream().filter(o -> o.getCustomerId() == Long.valueOf(customerId)).findFirst().get();
  }
  
  @Override
  public Customer patchEmail(String email, String customerId){
    if(!email.isBlank() && ! Objects.isNull(email) && !customerId.isBlank()){
      try{
        customerService.patchEmailById(email, Integer.valueOf(customerId));
      } catch (Exception nu){

      }
    }
    return customerService.patchEmailById(email, Integer.valueOf(customerId)).get();
  }
  
  @Override
  public Customer patchName(String name, String customerId){
    if(!name.isBlank() && ! Objects.isNull(name) && !customerId.isBlank()){
      try{
        customerService.patchNameById(name, Integer.valueOf(customerId));
      } catch (Exception nu){

      }
    }
    return customerService.patchNameById(name, Integer.valueOf(customerId)).get();
  }

  @Override
  public String remove(String customerId){
    if(!customerId.isBlank()){
      try{
        long id = Integer.valueOf(customerId);
         customerService.delete(id);
        return "Delete Successful";
      } catch (NullPointerException nu){

      }
    }
    return "Delete Successful";
  }

   @Override
  public Customer create( String name,String email,LocalDate dob){
    return customerService.create(name, email, dob);
  } 

  @Override
  public List<Customer> printCustomer(){
    return customerService.showCustomer();
  }

  @Override
  public Customer find(String customerId){
    if(!customerId.isBlank()){
      try{
        long id = Integer.valueOf(customerId);
        return customerService.find(id).orElse(null);
      } catch (Exception nu){

      }
    }
    return customerService.find(Integer.valueOf(customerId)).orElse(null);
  }

  @Override
  public Customer update(String customerId, Customer customer){
    if(!customerId.isBlank() && Objects.nonNull(customer)){
      try{
        long id = Integer.valueOf(customerId);
        return customerService.update(Integer.valueOf(customerId), customer);
      } catch (Exception nu){

      }
    }
    return customerService.update(Integer.valueOf(customerId), customer);
  }
}

