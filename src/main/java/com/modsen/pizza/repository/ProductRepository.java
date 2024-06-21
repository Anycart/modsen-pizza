package com.modsen.pizza.repository;

import com.modsen.pizza.entity.Category;
import com.modsen.pizza.entity.Order;
import com.modsen.pizza.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory_Id(Long id);
}
