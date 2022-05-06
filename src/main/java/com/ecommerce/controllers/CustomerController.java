package com.ecommerce.controllers;

import com.ecommerce.entities.Customer;
import com.ecommerce.entities.YesNo;
import com.ecommerce.services.CustomerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/customer")
@Tag(name = "Customer  API", description = "EndPoint for Customer")

public class CustomerController extends BaseController {
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers(@RequestParam(required = false, name = "username") String username, @RequestParam(required = false, name = "password") String password) {
        try {
            List<Customer> customerList = customerService.getList();
            if (customerList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(customerList, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Customer> findByCustomerId(@PathVariable long id) {
        Optional<Customer> optionalCustomer = customerService.findById(id);
        return optionalCustomer.map(customer -> new ResponseEntity<>(customer, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create")
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer ) {
        try {
            Customer saveCustomer = customerService.save(customer);
            return new ResponseEntity<>(saveCustomer, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @PutMapping("/updateCustomer/{id}")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
        if (customer.getId() != null) {
            Optional<Customer> optionalCustomer = customerService.findById(customer.getId());
            if (optionalCustomer.isPresent()) {
                Customer savedCustomer = optionalCustomer.get();
                savedCustomer = customerService.save(savedCustomer);
                return new ResponseEntity<>(savedCustomer, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/deleteCustomer/{id}")
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable("id") long id) {
        try {
            Optional<Customer> optionalCustomer = customerService.findById(id);
            if (optionalCustomer.isPresent()) {
                Customer saveCustomer = optionalCustomer.get();
                saveCustomer.setDeleted(YesNo.YES);
                customerService.save(saveCustomer);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
