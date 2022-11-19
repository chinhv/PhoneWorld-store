package com.group16.mystore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group16.mystore.entities.Cart;
import com.group16.mystore.entities.User;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart findByUser(User user);
}
