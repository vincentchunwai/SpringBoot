package com.example.demoshopping.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.example.demoshopping.model.Customer;
import com.example.demoshopping.model.Order;

public interface CustomerService {
  
  Customer create(String name, String email, LocalDate dob);

  Customer create(Customer customer);

  List<Customer> showCustomer();

  Optional<Customer> find(long customerId);

  Optional<Customer> patchEmailById(String email, long customerId);

  Optional<Customer> patchNameById(String name, long customerId);

  boolean delete(long customerId);

  Customer update(long customerId, Customer newCustomer);

  Order newOrder(long id);

  long findCustomerOrder(long id);

  List<Order> showOrders();
}
