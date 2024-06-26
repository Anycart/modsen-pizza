package com.modsen.pizza.dto;

import com.modsen.pizza.entity.OrderItem;
import com.modsen.pizza.entity.User;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Past;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDto {
    @Min(value = 0)
    private Long id;
    @Min(value = 0)
    private Long userId;
    @Past
    private LocalDateTime orderDate;
    @Min(value = 0)
    private Double totalAmount;

}
