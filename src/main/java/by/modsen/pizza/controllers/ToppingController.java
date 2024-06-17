package by.modsen.pizza.controllers;


import by.modsen.pizza.dto.ToppingDto;
import by.modsen.pizza.service.ToppingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/toppings")
public class ToppingController {

    private final ToppingService toppingService;

    @Autowired
    public ToppingController(ToppingService toppingService) {
        this.toppingService = toppingService;
    }

    @PostMapping
    public ResponseEntity<ToppingDto> createTopping(@RequestBody @Valid ToppingDto toppingDto) {
        ToppingDto createdTopping = toppingService.createTopping(toppingDto);
        return new ResponseEntity<>(createdTopping, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ToppingDto> updateTopping(@PathVariable Long id, @RequestBody @Valid ToppingDto toppingDto) {
        ToppingDto updatedTopping = toppingService.updateTopping(id, toppingDto);
        return new ResponseEntity<>(updatedTopping, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopping(@PathVariable Long id) {
        toppingService.deleteTopping(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToppingDto> getToppingById(@PathVariable Long id) {
        ToppingDto toppingDto = toppingService.getToppingById(id);
        return new ResponseEntity<>(toppingDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ToppingDto>> getAllToppings() {
        List<ToppingDto> toppings = toppingService.getAllToppings();
        return new ResponseEntity<>(toppings, HttpStatus.OK);
    }
}