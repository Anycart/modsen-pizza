package com.modsen.pizza;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import java.time.LocalDateTime;
import java.util.List;

import com.modsen.pizza.controller.OrderController;
import com.modsen.pizza.dto.repsonse.OrderResponseDto;
import com.modsen.pizza.dto.request.OrderRequestDto;
import com.modsen.pizza.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    @InjectMocks
    private OrderController orderController;

    @Mock
    private OrderService orderService;

    @Test
    public void testCreateOrder() {
        OrderRequestDto requestDto = new OrderRequestDto();
        // Установите поля для requestDto

        OrderResponseDto mockOrder = new OrderResponseDto();
        mockOrder.setId(1L);
        mockOrder.setOrderDate(LocalDateTime.now());
        mockOrder.setTotalAmount(100.0);
        // Установите другие поля для mockOrder

        when(orderService.createOrder(any(OrderRequestDto.class))).thenReturn(mockOrder);

        ResponseEntity<OrderResponseDto> response = orderController.createOrder(requestDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockOrder, response.getBody());
    }

    @Test
    public void testGetOrderById() {
        Long orderId = 1L;
        OrderResponseDto mockOrder = new OrderResponseDto();
        mockOrder.setId(orderId);
        mockOrder.setOrderDate(LocalDateTime.now());
        mockOrder.setTotalAmount(100.0);
        // Установите другие поля для mockOrder

        when(orderService.getOrderById(orderId)).thenReturn(mockOrder);

        ResponseEntity<OrderResponseDto> response = orderController.getOrderById(orderId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockOrder, response.getBody());
    }

    @Test
    public void testGetAllOrders() {
        List<OrderResponseDto> mockOrders = List.of(
                new OrderResponseDto(1L, LocalDateTime.now(), 100.0, List.of(/* order items */)),
                new OrderResponseDto(2L, LocalDateTime.now(), 200.0, List.of(/* order items */))
        );

        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "id"));

        when(orderService.getAllOrders(pageRequest)).thenReturn(mockOrders);

        ResponseEntity<List<OrderResponseDto>> response = orderController.getAllOrders(0, 10, "id");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockOrders, response.getBody());
    }

    @Test
    public void testGetOrdersByUserId() {
        Long userId = 1L;
        List<OrderResponseDto> mockOrders = List.of(
                new OrderResponseDto(1L, LocalDateTime.now(), 100.0, List.of(/* order items */)),
                new OrderResponseDto(2L, LocalDateTime.now(), 200.0, List.of(/* order items */))
        );

        when(orderService.getOrdersByUserId(userId)).thenReturn(mockOrders);

        ResponseEntity<List<OrderResponseDto>> response = orderController.getOrdersByUserId(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockOrders, response.getBody());
    }
}

