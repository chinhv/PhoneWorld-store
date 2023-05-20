package com.myproject.mystore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myproject.mystore.entities.Cart;
import com.myproject.mystore.entities.CartItems;
import com.myproject.mystore.entities.Product;

public interface CartItemRepository extends JpaRepository<CartItems,Integer> {
    CartItems findByCartAndProduct(Cart cart, Product product);
    List<CartItems> findAllByCart(Cart cart);
}
