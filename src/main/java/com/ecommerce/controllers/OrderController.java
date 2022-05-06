package com.ecommerce.controllers;

import com.ecommerce.entities.Order;
import com.ecommerce.entities.YesNo;
import com.ecommerce.services.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Orders API", description = "EndPoint for Orders")
public class OrderController extends BaseController {
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllUsers() {
        try {
            List<Order> orderList = orderService.getList();
            if (orderList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(orderList, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Order> findOrderById(@PathVariable long id) {
        Optional<Order> optionalOrder = orderService.findById(id);
        return optionalOrder.map(order -> new ResponseEntity<>(order, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@Valid @RequestBody Order order) {
        try {
            Order saveOrder = orderService.save(order);
            return new ResponseEntity<>(saveOrder, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @PutMapping("/updateOrder/{id}")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order) {
        if (order != null && order.getId() != null) {
            Optional<Order> optionalOrder = orderService.findById(order.getId());
            if (optionalOrder.isPresent()) {
                Order saveOrder = optionalOrder.get();
                saveOrder = orderService.save(saveOrder);
                return new ResponseEntity<>(saveOrder, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/deleteOrder/{id}")
    public ResponseEntity<HttpStatus> deleteOrder(@PathVariable("id") long id) {
        try {
            Optional<Order> optionalOrder = orderService.findById(id);
            if (optionalOrder.isPresent()) {
                Order saveOrder = optionalOrder.get();
                saveOrder.setDeleted(YesNo.YES);
                orderService.save(saveOrder);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
