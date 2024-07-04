package com.modsen.pizza.repository;

import com.modsen.pizza.entity.Order;
import com.modsen.pizza.entity.OrderItem;
import com.modsen.pizza.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class OrderItemRepositoryTest {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    private Product product;
    private Order order;
    private OrderItem orderItem;

    @BeforeEach
    public void setUp() {
        product = Product.builder()
                .name("Pizza Margherita")
                .description("Classic pizza with tomato sauce and mozzarella cheese")
                .price(10.0)
                .build();
        productRepository.saveAndFlush(product);

        order = Order.builder()
                .orderDate(LocalDateTime.now())
                .totalAmount(20.0)
                .build();
        orderRepository.saveAndFlush(order);

        orderItem = OrderItem.builder()
                .order(order)
                .product(product)
                .quantity(2)
                .price(product.getPrice() * 2)
                .build();
    }

    @Test
    public void testSaveOrderItem() {
        orderItemRepository.saveAndFlush(orderItem);

        assertThat(orderItem.getId()).isNotNull();

        Optional<OrderItem> savedOrderItemOptional = orderItemRepository.findById(orderItem.getId());
        assertThat(savedOrderItemOptional).isPresent();

        OrderItem savedOrderItem = savedOrderItemOptional.get();
        assertThat(savedOrderItem.getOrder()).isEqualTo(order);
        assertThat(savedOrderItem.getProduct()).isEqualTo(product);
        assertThat(savedOrderItem.getQuantity()).isEqualTo(2);
        assertThat(savedOrderItem.getPrice()).isEqualTo(20.0);
    }

    @Test
    public void testUpdateOrderItem() {
        orderItemRepository.saveAndFlush(orderItem);

        orderItem.setQuantity(3);
        orderItemRepository.saveAndFlush(orderItem);

        Optional<OrderItem> updatedOrderItemOptional = orderItemRepository.findById(orderItem.getId());
        assertThat(updatedOrderItemOptional).isPresent();
        OrderItem updatedOrderItem = updatedOrderItemOptional.get();
        assertThat(updatedOrderItem.getQuantity()).isEqualTo(3);
    }

    @Test
    public void testDeleteOrderItem() {
        orderItemRepository.saveAndFlush(orderItem);

        orderItemRepository.delete(orderItem);

        Optional<OrderItem> deletedOrderItemOptional = orderItemRepository.findById(orderItem.getId());
        assertThat(deletedOrderItemOptional).isEmpty();
    }

    @Test
    public void testFindAllOrderItems() {
        orderItemRepository.saveAndFlush(orderItem);

        List<OrderItem> orderItems = orderItemRepository.findAll();

        assertThat(orderItems).hasSize(1);
    }
}
