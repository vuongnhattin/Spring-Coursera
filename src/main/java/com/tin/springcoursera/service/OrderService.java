package com.tin.springcoursera.service;

import com.tin.springcoursera.entity.Orders;
import com.tin.springcoursera.exception.AppException;
import com.tin.springcoursera.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public void createOrder(int userId, int courseId, String token) {
        Orders order = Orders.builder()
                .userId(userId)
                .courseId(courseId)
                .token(token)
                .build();
        orderRepository.save(order);
    }

    public void deleteOrderByToken(String token) {
        orderRepository.deleteByToken(token);
    }

    public Orders getOrderByToken(String token) {
        return orderRepository.findByToken(token).orElseThrow(() -> new AppException(404, "Order not found"));
    }
}
