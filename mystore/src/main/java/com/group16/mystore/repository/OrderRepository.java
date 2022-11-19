package com.group16.mystore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.group16.mystore.entities.Order;
import com.group16.mystore.entities.User;

public interface OrderRepository extends JpaRepository<Order,Integer>, QuerydslPredicateExecutor<Order> {
    List<Order> findByUser(User user);
}
