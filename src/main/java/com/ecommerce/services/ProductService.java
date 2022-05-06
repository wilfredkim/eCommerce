package com.ecommerce.services;

import com.ecommerce.entities.Product;

import java.util.Optional;

public interface ProductService {
    Product save(Product product);

    Optional<Product> findById(Long id);


}
