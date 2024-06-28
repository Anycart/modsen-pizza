package com.modsen.pizza.util;

import com.modsen.pizza.dto.repsonse.OrderItemResponseDto;
import com.modsen.pizza.dto.repsonse.OrderResponseDto;
import com.modsen.pizza.dto.repsonse.ProductResponseDto;
import com.modsen.pizza.entity.Order;
import com.modsen.pizza.entity.OrderItem;
import com.modsen.pizza.entity.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderToOrderResponseDtoConverter implements Converter<Order, OrderResponseDto> {

    @Override
    public OrderResponseDto convert(Order source) {
        List<OrderItemResponseDto> orderItemResponseDtos = source.getOrders().stream()
                .map(this::convertOrderItem)
                .collect(Collectors.toList());

        return OrderResponseDto.builder()
                .id(source.getId())
                .orderDate(source.getOrderDate())
                .totalAmount(source.getTotalAmount())
                .orderItems(orderItemResponseDtos)
                .build();
    }

    private OrderItemResponseDto convertOrderItem(OrderItem orderItem) {
        return OrderItemResponseDto.builder()
                .id(orderItem.getId())
                .quantity(orderItem.getQuantity())
                .price(orderItem.getPrice())
                .productResponseDto(convertProduct(orderItem.getProduct()))
                .build();
    }

    private ProductResponseDto convertProduct(Product product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .build();
    }
}
