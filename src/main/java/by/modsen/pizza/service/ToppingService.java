package by.modsen.pizza.service;


import by.modsen.pizza.dto.ToppingDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ToppingService {

    public ToppingDto createTopping(ToppingDto toppingDto) {
        return toppingDto;
    }


    public ToppingDto updateTopping(Long id, ToppingDto toppingDto) {
        return toppingDto;
    }

    public void deleteTopping(Long id) {

    }

    public ToppingDto getToppingById(Long id) {

        return new ToppingDto();
    }

    public List<ToppingDto> getAllToppings() {
        return new ArrayList<>();
    }
}