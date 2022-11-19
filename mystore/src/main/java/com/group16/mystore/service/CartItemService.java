package com.group16.mystore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group16.mystore.entities.Cart;
import com.group16.mystore.entities.CartItems;
import com.group16.mystore.entities.Product;
import com.group16.mystore.repository.CartItemRepository;

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
