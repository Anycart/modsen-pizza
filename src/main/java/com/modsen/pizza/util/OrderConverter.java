package com.modsen.pizza.util;

import com.modsen.pizza.dto.OrderDto;
import com.modsen.pizza.entity.Order;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderConverter {
    private final ModelMapper modelMapper;
    public Order convertToOrder(OrderDto orderDto){
        return modelMapper.map(orderDto, Order.class);
    }
    public OrderDto convertToOrderDTO(Order order){
        return modelMapper.map(order, OrderDto.class);
    }
}

