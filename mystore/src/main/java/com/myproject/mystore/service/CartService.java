package com.myproject.mystore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.mystore.entities.Cart;
import com.myproject.mystore.entities.User;
import com.myproject.mystore.repository.CartRepository;

@Service
public class CartService{
    @Autowired
    private CartRepository cartRepository;

    public Cart getCartByUser(User user) {
        return cartRepository.findByUser(user);
    }

    public Cart getCartById(int id){
        return cartRepository.findById(id).orElse(null);
    }

    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }
}