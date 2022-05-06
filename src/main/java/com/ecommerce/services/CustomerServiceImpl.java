package com.ecommerce.services;

import com.ecommerce.entities.Customer;
import com.ecommerce.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository repository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Customer save(Customer customer) {
        customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
        return repository.save(customer);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Customer> getList() {
        return repository.findAll();
    }
}
