package com.example.demoshopping.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demoshopping.database.CustomerDatabase;
import com.example.demoshopping.database.OrderDatabase;
import com.example.demoshopping.model.Customer;
import com.example.demoshopping.model.Order;
import com.example.demoshopping.service.CustomerService;

import lombok.ToString;

@Service
public class CustomerServiceImpl implements CustomerService{
  
  public Customer create(String name, String email, LocalDate dob){
    Customer customer = Customer.builder()
      .email(email)
      .dob(dob)
      .name(name)
      .build();
    CustomerDatabase.add(customer);
    return customer;
  }

  @Override
  public List<Customer> showCustomer(){
    return CustomerDatabase.customers;
  }

  @Override
  public Optional<Customer> find(long customerId){
    return CustomerDatabase.find(customerId);
  }

  @Override
  public boolean delete(long customerId){
    return CustomerDatabase.delete(customerId);
  }

  @Override
  public Optional<Customer> patchEmailById(String email, long customerId){
    return CustomerDatabase.patchEmailById(email, customerId);
  }

  @Override
  public Optional<Customer> patchNameById(String name, long customerId){
    return CustomerDatabase.patchNameById(name, customerId);
  }

  @Override
  public Customer create(Customer customer){
    CustomerDatabase.add(customer);
    return customer;
  }

  @Override
  public Customer update(long customerId, Customer newCustomer){
    return CustomerDatabase.update(customerId, newCustomer);
  }

  @Override
  public Order newOrder(long id){
    if (find(id).isPresent()){
    return OrderDatabase.Ordering(id);
    }
    else{
      return null;
    }

  }

  @Override
  public long findCustomerOrder(long id){
    return OrderDatabase.findCustomerOrder(id);
  }

  @Override
  public List<Order> showOrders(){
    return OrderDatabase.orders;
  }
}
