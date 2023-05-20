package com.myproject.mystore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myproject.mystore.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{
    User findByUserName(String userName);
    User findByEmail(String email);
    Boolean existsByEmail(String email);
}
