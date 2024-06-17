package by.modsen.pizza.service;


import by.modsen.pizza.dto.OrderDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class OrderService {

    public OrderDto createOrder(OrderDto orderDto) {
        return new OrderDto();
    }


    public OrderDto getOrderById( Long id) {
        return new OrderDto();
    }


    public List<OrderDto> getAllOrders() {

        return new ArrayList<>();
    }


    public List<OrderDto> getOrdersByUserId(Long userId) {

        return new ArrayList();
    }
}
