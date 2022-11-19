package com.group16.mystore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.group16.mystore.dto.OrderDto;
import com.group16.mystore.dto.SearchOrderObject;
import com.group16.mystore.entities.Order;
import com.group16.mystore.entities.QOrder;
import com.group16.mystore.entities.User;
import com.group16.mystore.repository.OrderRepository;
import com.querydsl.core.BooleanBuilder;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;

    public Order convertOrderDtoToOrder(OrderDto orderDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.getUserByEmail(auth.getName());
        Order order = new Order();
        order.setUserName(orderDto.getUserName());
        order.setAddress(orderDto.getAddress());
        order.setPhone(orderDto.getPhone());
        order.setNote(orderDto.getNote());
        order.setAcception("Chưa duyệt !");
        order.setUser(currentUser);
        return order;
    }

    public Page<Order> getAllOrders(int page, int pageSize){
        return orderRepository.findAll(PageRequest.of(page, pageSize));
    }

    public Page<Order> getOrdersByFilter(SearchOrderObject obj, int page, int pageSize){
        String userName = obj.getUserName();
        String status = obj.getStatus();
        BooleanBuilder builder = new BooleanBuilder();
        if(!userName.equals("")){
            builder.and(QOrder.order.userName.eq(userName));
        }
        if(!status.equals("")){
            builder.and(QOrder.order.acception.eq(status));
        }
        return orderRepository.findAll(builder, PageRequest.of(page, pageSize));
    }

    public Order updateOrder(Order order){
        Order currentOrder = orderRepository.findById(order.getId()).get();
        currentOrder.setUserName(order.getUserName());
        currentOrder.setAddress(order.getAddress());
        currentOrder.setPhone(order.getPhone());
        return orderRepository.save(currentOrder);
    }

    public Order getOrderById(int id){
        return orderRepository.findById(id).get();
    }

    public Order saveOrderByUser(OrderDto orderDto){
        return orderRepository.save(convertOrderDtoToOrder(orderDto));
    }

    public Order saveOrder(Order order){
        return orderRepository.save(order);
    }

    public String deleteOrder(int id){
        orderRepository.deleteById(id);
        return "delete done !";
    }
}
