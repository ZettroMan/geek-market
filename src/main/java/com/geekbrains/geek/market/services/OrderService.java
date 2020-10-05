package com.geekbrains.geek.market.services;

import com.geekbrains.geek.market.entities.Order;
import com.geekbrains.geek.market.entities.OrderItem;
import com.geekbrains.geek.market.repositories.OrderItemsRepository;
import com.geekbrains.geek.market.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;
    private OrderItemsRepository orderItemsRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order saveOrUpdate(Order order) {
        return orderRepository.save(order);
    }

    public OrderItem saveOrderItem(OrderItem orderItem) {
        return orderItemsRepository.save(orderItem);
    }

    public void remove(Order order) {
        orderRepository.delete(order);
        orderRepository.flush();
    }

}
