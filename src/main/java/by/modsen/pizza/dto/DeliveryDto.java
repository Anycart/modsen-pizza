package by.modsen.pizza.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDto {
    private Long id;
    private String name;
    private double price;
    private String description;
}
