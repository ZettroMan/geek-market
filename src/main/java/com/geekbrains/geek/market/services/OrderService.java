package com.geekbrains.geek.market.services;

import com.geekbrains.geek.market.entities.Order;
import com.geekbrains.geek.market.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public List<Order> findAllByUserName(String username) {
        return orderRepository.findAllOrdersByUserName(username);
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }
}
