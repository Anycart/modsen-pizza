package by.modsen.pizza.service;



import by.modsen.pizza.dto.DeliveryDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeliveryService {

    public DeliveryDto createDelivery(DeliveryDto deliveryDto) {
        return deliveryDto;
    }

    public DeliveryDto updateDelivery(Long id, DeliveryDto deliveryDto) {
        return deliveryDto;
    }

    public void deleteDelivery(Long id) {

    }

    public DeliveryDto getDeliveryById(Long id) {
        return new DeliveryDto();
    }

    public List<DeliveryDto> getAllDeliveries() {
        return new ArrayList<>();
    }
}
