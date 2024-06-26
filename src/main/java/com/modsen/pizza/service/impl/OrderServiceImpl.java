package com.modsen.pizza.service.impl;

import com.modsen.pizza.dto.OrderDto;
import com.modsen.pizza.entity.Order;
import com.modsen.pizza.entity.User;
import com.modsen.pizza.repository.OrderRepository;
import com.modsen.pizza.repository.UserRepository;
import com.modsen.pizza.service.OrderService;
import com.modsen.pizza.util.OrderConverter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderConverter orderConverter;
    private final UserRepository userRepository;

    @Override
    public OrderDto createOrder(@Valid OrderDto orderDto) {
        User user = userRepository.findById(orderDto.getUserId()).orElseThrow(NoSuchElementException::new);
        Order order = orderRepository.save(orderConverter.convertToOrder(orderDto));
        order.setUser(user);
        return orderConverter.convertToOrderDTO(order);
    }

    @Override
    @Transactional(readOnly = true)
    public OrderDto getOrderById(Long id) {
        return orderRepository.findById(id)
                .map(orderConverter::convertToOrderDTO)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDto> getAllOrders(Pageable pageable) {
        return orderRepository.findAll()
                .stream()
                .map(orderConverter::convertToOrderDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getOrdersByUserId(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(NoSuchElementException::new)
                .getOrders()
                .stream()
                .map(orderConverter::convertToOrderDTO)
                .collect(Collectors.toList());
    }
}
