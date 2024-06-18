package by.modsen.pizza.service.impl;

import by.modsen.pizza.dto.OrderDto;
import by.modsen.pizza.service.OrderService;
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
    public List<OrderDto> getAllOrders() {
        return null;
    }

    @Override
    public List<OrderDto> getOrdersByUserId(Long userId) {
        return null;
    }
}
