package com.modsen.pizza.service;

import com.modsen.pizza.dto.repsonse.OrderResponseDto;
import com.modsen.pizza.dto.request.OrderRequestDto;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface OrderService {
    OrderResponseDto createOrder(OrderRequestDto requestDto);
    OrderResponseDto getOrderById(Long id);
    List<OrderResponseDto> getAllOrders(Pageable pageable);
    List<OrderResponseDto> getOrdersByUserId(Long userId);
}
