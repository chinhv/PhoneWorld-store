package com.group16.mystore.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group16.mystore.entities.Cart;
import com.group16.mystore.entities.CartItems;
import com.group16.mystore.entities.Product;
import com.group16.mystore.entities.User;
import com.group16.mystore.service.CartItemService;
import com.group16.mystore.service.CartService;
import com.group16.mystore.service.ProductService;
import com.group16.mystore.service.UserService;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    public User getCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.getUserByEmail(auth.getName());
    }

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> getCartItems(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = getCurrentUser();
        Map<String, Object> response = new HashMap<>();
        if(auth == null){
            response.put("message", "return form log-in !");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }else{
            Cart cart = cartService.getCartByUser(currentUser);
            List<CartItems> items = cartItemService.getListItemByCart(cart);
            if(items.isEmpty()){
                response.put("message", "No product yet !");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }else{
                List<Product> products = new ArrayList<>();
                Map<Integer, String> countProduct = new HashMap<>();
                long totalPayment = 0;
                for(CartItems item : items){
                    products.add(item.getProduct());
                    countProduct.put(item.getProduct().getId(), item.getCount());
                    totalPayment = totalPayment + item.getProduct().getPrice() * Integer.parseInt(item.getCount());
                }
                response.put("totalPrice", totalPayment);
                response.put("count", countProduct);
                response.put("products", products);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
    }

    @PostMapping("/addToCart")
    public ResponseEntity<String> addToCart(@RequestParam(required = false, value = "id") String id, @RequestParam(defaultValue = "1") String count){
        Product product = productService.getProductById(Integer.parseInt(id));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser =getCurrentUser();
        if(auth == null){
            return new ResponseEntity<>("return form log-in", HttpStatus.BAD_REQUEST);
        }else{
            Cart cart = cartService.getCartByUser(currentUser);
            if(cart == null){
                cart = new Cart();
                cart.setUser(currentUser);
                cart = cartService.saveCart(cart);
            }
            CartItems item = cartItemService.getCartItemByCartAndProduct(cart,product);
            if(item == null){
                item = new CartItems();
                item.setCart(cart);
                item.setProduct(product);
                item.setCount(count);
            }else{
                item.setCount(item.getCount() + 1);
            }
            item = cartItemService.addCartItems(item);
        }
        return new ResponseEntity<>("Saved to cart", HttpStatus.OK);
    }

    @DeleteMapping("/deleteItem")
    public ResponseEntity<String> deleteCartItems(@RequestParam(required = false, value = "id") String id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Product product = productService.getProductById(Integer.parseInt(id));
        User currentUser = userService.getUserByEmail(auth.getName());
        Cart cart = cartService.getCartByUser(currentUser);
        CartItems item = cartItemService.getCartItemByCartAndProduct(cart, product);
        cartItemService.deleteCartItems(item);
        return new ResponseEntity<>("delete done !", HttpStatus.OK);
    }
}
