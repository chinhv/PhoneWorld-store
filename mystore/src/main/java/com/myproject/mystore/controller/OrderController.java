package com.myproject.mystore.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.mystore.dto.OrderDto;
import com.myproject.mystore.dto.SearchOrderObject;
import com.myproject.mystore.entities.Order;
import com.myproject.mystore.service.OrderService;

@CrossOrigin(origins = "http://127.0.0.1:5500", allowCredentials = "true")
@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> findOrderByFilter(
        @RequestParam(defaultValue = "") String username, 
        @RequestParam(defaultValue = "") String status, 
        @RequestParam(defaultValue = "0") int page, 
        @RequestParam(defaultValue = "3")int pageSize
    ){
        
        SearchOrderObject obj = new SearchOrderObject();
        obj.setUserName(username);
        obj.setStatus(status);
        List<Order> orders = new ArrayList<>();
        Page<Order> pageTuts = orderService.getOrdersByFilter(obj, page, pageSize);
        orders = pageTuts.getContent();
        Map<String, Object> response = new HashMap<>();
        response.put("orders", orders);
        response.put("currentPage", pageTuts.getNumber());
        response.put("totalPage", pageTuts.getTotalPages());
        response.put("totalElements", pageTuts.getTotalElements());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/changeInfo")
    public Order changeOrderInfo(@RequestBody Order order){
        return orderService.updateOrder(order);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addOrder(@RequestBody OrderDto dto, @RequestParam(required = false, value = "userId") int userId){
        orderService.saveOrderByUser(dto, userId);
        return new ResponseEntity<>("Order success!", HttpStatus.OK);
    }

    @PostMapping("/access")
    public ResponseEntity<String> updateOrderStatus(@RequestParam(required = false, value = "id") int id){
        Order order = orderService.getOrderById(id);
        order.setAcception("Đã xác nhận !");
        orderService.saveOrder(order);
        return new ResponseEntity<>("Success!", HttpStatus.OK);
    }

    @PostMapping("/cancel")
    public Order cancelOrder(@RequestParam(required = false, value = "id")int id){
        Order order = orderService.getOrderById(id);
        order.setAcception("Đã hủy đơn !");
        return orderService.saveOrder(order);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteOrder(@PathVariable int id){
        return orderService.deleteOrder(id);
    }

    
}
