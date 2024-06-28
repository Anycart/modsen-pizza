package com.modsen.pizza.util;

import com.modsen.pizza.dto.request.OrderItemRequestDto;
import com.modsen.pizza.dto.request.OrderRequestDto;
import com.modsen.pizza.entity.Order;
import com.modsen.pizza.entity.OrderItem;
import com.modsen.pizza.entity.Product;
import com.modsen.pizza.entity.User;
import com.modsen.pizza.repository.ProductRepository;
import com.modsen.pizza.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderRequestDtoToOrderConverter implements Converter<OrderRequestDto, Order> {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public Order convert(OrderRequestDto source) {
        User user = userRepository.findById(source.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + source.getUserId()));

        List<OrderItem> orderItems = source.getOrderItems().stream()
                .map(this::convertOrderItem)
                .collect(Collectors.toList());

        Order order = Order.builder()
                .user(user)
                .orderDate(source.getOrderDate())
                .totalAmount(source.getTotalAmount())
                .orders(orderItems)
                .build();

        orderItems.forEach(orderItem -> orderItem.setOrder(order));

        return order;
    }

    private OrderItem convertOrderItem(OrderItemRequestDto orderItemRequestDto) {
        Product product = productRepository.findById(orderItemRequestDto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + orderItemRequestDto.getProductId()));

        return OrderItem.builder()
                .quantity(orderItemRequestDto.getQuantity())
                .price(orderItemRequestDto.getPrice())
                .product(product)
                .build();
    }
}
