package com.modsen.pizza.service;

import com.modsen.pizza.dto.OrderDto;
import com.modsen.pizza.entity.Order;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface OrderService {
    void createOrder(OrderDto orderDto);
    Order getOrderById(Long id);
    List<Order> getAllOrders(Pageable pageable);
    List<Order> getOrdersByUserId(Long userId);
}
