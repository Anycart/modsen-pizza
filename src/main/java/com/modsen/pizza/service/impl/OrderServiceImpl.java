package com.modsen.pizza.service.impl;

import com.modsen.pizza.dto.OrderDto;
import com.modsen.pizza.service.OrderService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        return null;
    }

    @Override
    public OrderDto getOrderById(Long id) {
        return null;
    }

    @Override
    public List<OrderDto> getAllOrders(Pageable pageable) {
        return null;
    }

    @Override
    public List<OrderDto> getOrdersByUserId(Long userId) {
        return null;
    }
}
