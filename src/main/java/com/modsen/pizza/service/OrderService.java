package com.modsen.pizza.service;

import com.modsen.pizza.dto.OrderDto;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto);
    OrderDto getOrderById(Long id);
    List<OrderDto> getAllOrders(Pageable pageable);
    List<OrderDto> getOrdersByUserId(Long userId);
}
