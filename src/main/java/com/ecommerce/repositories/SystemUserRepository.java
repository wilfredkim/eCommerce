package com.ecommerce.repositories;

import com.ecommerce.entities.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface SystemUserRepository extends JpaRepository<SystemUser, Long> {
    Optional<SystemUser> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
