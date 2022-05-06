package com.ecommerce.controllers;

import com.ecommerce.services.OrderService;
import com.ecommerce.services.ProductCategoryService;
import com.ecommerce.services.ProductService;
import com.ecommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

    private OrderService orderService;

    private ProductService productService;

    private ProductCategoryService productCategoryService;

    private UserService userService;

    @Autowired
    public BaseController(OrderService orderService, ProductService productService, ProductCategoryService productCategoryService, UserService userService) {
        this.orderService = orderService;
        this.productService = productService;
        this.productCategoryService = productCategoryService;
        this.userService = userService;
    }
}
