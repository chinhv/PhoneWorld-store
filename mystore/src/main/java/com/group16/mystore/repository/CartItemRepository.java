package com.group16.mystore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group16.mystore.entities.Cart;
import com.group16.mystore.entities.CartItems;
import com.group16.mystore.entities.Product;

public interface CartItemRepository extends JpaRepository<CartItems,Integer> {
    CartItems findByCartAndProduct(Cart cart, Product product);
    List<CartItems> findAllByCart(Cart cart);
}
