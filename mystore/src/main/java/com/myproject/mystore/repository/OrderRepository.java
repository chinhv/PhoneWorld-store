package com.myproject.mystore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.myproject.mystore.entities.Cart;
import com.myproject.mystore.entities.Order;


public interface OrderRepository extends JpaRepository<Order,Integer>, QuerydslPredicateExecutor<Order> {
    List<Order> findByCart(Cart cart);
}
