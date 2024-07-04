package com.modsen.pizza.repository;

import com.modsen.pizza.entity.Order;
import com.modsen.pizza.entity.User;
import com.modsen.pizza.enumeration.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveOrder() {
        User user = User.builder()
                .username("testuser")
                .email("testuser@example.com")
                .password("password")
                .fullName("Test User")
                .sex("Male")
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .role(Role.USER)
                .build();

        Order order = Order.builder()
                .user(user)
                .orderDate(LocalDateTime.now())
                .totalAmount(100.0)
                .build();

        orderRepository.save(order);

        assertThat(order.getId()).isNotNull();

        Optional<Order> savedOrderOptional = orderRepository.findById(order.getId());
        assertThat(savedOrderOptional).isPresent();


        Order savedOrder = savedOrderOptional.get();
        assertThat(savedOrder.getUser()).isEqualTo(user);
        assertThat(savedOrder.getOrderDate()).isNotNull();
        assertThat(savedOrder.getTotalAmount()).isEqualTo(100.0);
    }


    @Test
    public void testUpdateOrder() {
        Order order = Order.builder()
                .orderDate(LocalDateTime.now())
                .totalAmount(100.0)
                .build();

        orderRepository.save(order);

        order.setTotalAmount(150.0);
        orderRepository.save(order);

        Optional<Order> updatedOrderOptional = orderRepository.findById(order.getId());
        assertThat(updatedOrderOptional).isPresent();

        Order updatedOrder = updatedOrderOptional.get();
        assertThat(updatedOrder.getTotalAmount()).isEqualTo(150.0);
    }

    @Test
    public void testDeleteOrder() {
        Order order = Order.builder()
                .orderDate(LocalDateTime.now())
                .totalAmount(100.0)
                .build();

        orderRepository.save(order);

        orderRepository.delete(order);

        Optional<Order> deletedOrderOptional = orderRepository.findById(order.getId());
        assertThat(deletedOrderOptional).isEmpty();
    }

    @Test
    public void testFindByUserId() {
        User user = User.builder()
                .username("testuser")
                .email("testuser@example.com")
                .password("password")
                .fullName("Test User")
                .sex("Male")
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .role(Role.USER)
                .build();

        User savedUser = userRepository.save(user);

        Order order1 = Order.builder()
                .user(savedUser)
                .orderDate(LocalDateTime.now())
                .totalAmount(100.0)
                .build();

        Order order2 = Order.builder()
                .user(savedUser)
                .orderDate(LocalDateTime.now())
                .totalAmount(150.0)
                .build();

        orderRepository.save(order1);
        orderRepository.save(order2);

        List<Order> ordersByUserId = orderRepository.findByUser_Id(savedUser.getId());

        assertThat(ordersByUserId).isNotEmpty();
        assertThat(ordersByUserId).contains(order1, order2);
    }


}
