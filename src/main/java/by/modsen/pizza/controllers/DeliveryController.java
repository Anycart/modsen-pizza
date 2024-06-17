package by.modsen.pizza.controllers;


import by.modsen.pizza.dto.DeliveryDto;
import by.modsen.pizza.service.DeliveryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;

    @Autowired
    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping
    public ResponseEntity<DeliveryDto> createDelivery(@RequestBody @Valid DeliveryDto deliveryDto) {
        DeliveryDto createdDelivery = deliveryService.createDelivery(deliveryDto);
        return new ResponseEntity<>(createdDelivery, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeliveryDto> updateDelivery(@PathVariable Long id, @RequestBody @Valid DeliveryDto deliveryDto) {
        DeliveryDto updatedDelivery = deliveryService.updateDelivery(id, deliveryDto);
        return new ResponseEntity<>(updatedDelivery, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDelivery(@PathVariable Long id) {
        deliveryService.deleteDelivery(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryDto> getDeliveryById(@PathVariable Long id) {
        DeliveryDto deliveryDto = deliveryService.getDeliveryById(id);
        return new ResponseEntity<>(deliveryDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<DeliveryDto>> getAllDeliveries() {
        List<DeliveryDto> deliveries = deliveryService.getAllDeliveries();
        return new ResponseEntity<>(deliveries, HttpStatus.OK);
    }
}
