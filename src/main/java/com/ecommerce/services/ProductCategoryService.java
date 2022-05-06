package com.ecommerce.services;

import com.ecommerce.entities.ProductCategory;

import java.util.Optional;

public interface ProductCategoryService {

    ProductCategory save(ProductCategory productCategory);

    Optional<ProductCategory> findById(Long id);




}
