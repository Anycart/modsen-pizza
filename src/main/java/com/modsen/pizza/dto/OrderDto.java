package com.modsen.pizza.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDto implements Serializable {
    @Positive(message = "Order ID must be a positive number")
    private Long id;

    @Positive(message = "User ID must be a positive number")
    private Long userId;

    @NotNull(message = "Created at date cannot be null")
    private LocalDateTime createdAt;

    @Valid
    @Size(min = 1, message = "Order must have at least one item")
    private List<OrderItemDto> items;

    @NotNull(message = "Status cannot be null")
    @Size(min = 3, max = 50, message = "Status must be between 3 and 50 characters")
    private String status;
}