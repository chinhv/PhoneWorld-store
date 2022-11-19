package com.group16.mystore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group16.mystore.entities.Cart;
import com.group16.mystore.entities.User;
import com.group16.mystore.repository.CartRepository;

@Service
public class CartService{
    @Autowired
    private CartRepository cartRepository;

    public Cart getCartByUser(User user) {
        return cartRepository.findByUser(user);
    }

    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }
}