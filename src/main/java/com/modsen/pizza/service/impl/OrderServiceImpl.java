package com.modsen.pizza.service.impl;

import com.modsen.pizza.dto.OrderDto;
import com.modsen.pizza.entity.Order;
import com.modsen.pizza.repository.OrderRepository;
import com.modsen.pizza.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    @Override
    public void createOrder(OrderDto orderDto) {
        orderRepository.save(modelMapper.map(orderDto, Order.class));
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Order> getAllOrders(Pageable pageable) {
        return null;
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUser_Id(userId);
    }
}
