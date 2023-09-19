package com.example.demoshopping.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demoshopping.model.Customer;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CustomerDatabase {

  public static List<Customer> customers = new ArrayList<>();

  public static void add(Customer customer){
    customers.add(Customer
      .builder()
      .name(customer.getName())
      .email(customer.getEmail())
      .dob(customer.getDob())
      .build());
  }

  public static Optional<Customer> find(long id){
    return customers.stream().filter(c -> c.getId() == id).findFirst();
  }

  public static boolean delete(long id){
    
     boolean successful = customers.removeIf((cust) -> cust.getId()==id );
      
     return successful;
  }

  public static Optional<Customer> patchEmailById(String email, long id){
    Optional<Customer> customer = CustomerDatabase.find(id);
    Optional<Customer> target = null;
    if(customers.contains(customer.get())){
      for(int i = 0; i < customers.size(); i++){
        if (customers.get(i).equals(customer.get())){
          customers.get(i).setEmail(email);
          target = Optional.ofNullable(customers.get(i));
        }
      }
    }
    return target;
  }

  public static Optional<Customer> patchNameById(String name, long id){
    Optional<Customer> customer = CustomerDatabase.find(id);
    Optional<Customer> target = null;
    if(customer.isPresent() && customers.contains(customer.get())){
      for(int i = 0; i < customers.size(); i++){
        if (customers.get(i).equals(customer.get())){
          customers.get(i).setName(name);
          target = Optional.ofNullable(customers.get(i));
        }
      }
    }
    return target;
  }

  public static Customer update(long id, Customer newCustomer){
    Optional<Customer> customer = find(id);
    
    if(customer.isPresent() && customers.contains(customer.get())){
      customers.stream().filter(cus -> cus.getId() == id)
      .forEach( e -> {
        e.setDob(newCustomer.getDob());
        e.setEmail(newCustomer.getEmail());
        e.setName(newCustomer.getName());
      });
    }
    else{
      return null;
    }
    return newCustomer;
  }


}

