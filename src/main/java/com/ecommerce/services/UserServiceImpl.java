package com.ecommerce.services;

import com.ecommerce.entities.User;
import com.ecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<User> getList() {
        return repository.findAll();
    }
}
