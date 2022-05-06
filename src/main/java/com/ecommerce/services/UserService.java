package com.ecommerce.services;

import com.ecommerce.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User save(User user);

    Optional<User> findById(Long id);

    List<User> getList();

}
