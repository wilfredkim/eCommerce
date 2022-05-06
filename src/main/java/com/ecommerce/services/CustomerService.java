package com.ecommerce.services;

import com.ecommerce.entities.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Customer save(Customer customer);

    Optional<Customer> findById(Long id);

    List<Customer> getList();

}
