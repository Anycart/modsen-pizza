package com.modsen.pizza.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDto {
    @Min(value = 0)
    private Long userId;
    private LocalDateTime orderDate;
    @Min(value = 0)
    private Double totalAmount;
    @Valid
    private List<OrderItemRequestDto> orderItems;

}
