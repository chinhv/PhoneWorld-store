package com.myproject.mystore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myproject.mystore.entities.Cart;
import com.myproject.mystore.entities.User;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart findByUser(User user);
}
