package com.modsen.pizza.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemRequestDto {
    @Min(value = 0)
    private Integer quantity;
    @Min(value = 0)
    private Double price;
    @Min(value = 0)
    private Long productId;
}
