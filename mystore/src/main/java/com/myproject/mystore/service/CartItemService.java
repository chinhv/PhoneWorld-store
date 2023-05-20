package com.myproject.mystore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.mystore.entities.Cart;
import com.myproject.mystore.entities.CartItems;
import com.myproject.mystore.entities.Product;
import com.myproject.mystore.repository.CartItemRepository;

@Service
public class CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;

    public CartItems getCartItemByCartAndProduct(Cart cart, Product product) {
        return cartItemRepository.findByCartAndProduct(cart, product);
    }

    public List<CartItems> getListItemByCart(Cart cart) {
        return cartItemRepository.findAllByCart(cart);
    }

    public CartItems addCartItems(CartItems item){
        return cartItemRepository.save(item);
    }

    public String deleteCartItems(CartItems item){
        cartItemRepository.delete(item);
        return "delete done !";
    }
}
