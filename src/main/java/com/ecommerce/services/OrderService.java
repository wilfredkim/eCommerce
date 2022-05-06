package com.ecommerce.services;

import com.ecommerce.entities.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<Order> getList();

    Order save(Order order);

    Optional<Order> findById(Long id);

}
