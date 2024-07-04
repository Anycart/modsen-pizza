package com.modsen.pizza.repository;

import com.modsen.pizza.entity.User;
import com.modsen.pizza.enumeration.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveUser() {

        User user = User.builder()
                .username("testuser")
                .email("testuser@example.com")
                .password("password")
                .fullName("Test User")
                .sex("Male")
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .role(Role.USER)
                .build();


        userRepository.save(user);

        assertThat(user.getId()).isNotNull();

        Optional<User> savedUserOptional = userRepository.findById(user.getId());
        assertThat(savedUserOptional).isPresent();

        User savedUser = savedUserOptional.get();
        assertThat(savedUser.getUsername()).isEqualTo("testuser");
        assertThat(savedUser.getEmail()).isEqualTo("testuser@example.com");
        assertThat(savedUser.getPassword()).isEqualTo("password");
        assertThat(savedUser.getFullName()).isEqualTo("Test User");
        assertThat(savedUser.getSex()).isEqualTo("Male");
        assertThat(savedUser.getDateOfBirth()).isEqualTo(LocalDate.of(1990, 1, 1));
        assertThat(savedUser.getRole()).isEqualTo(Role.USER);
    }

    @Test
    public void testUpdateUser() {

        User user = User.builder()
                .username("testuser")
                .email("testuser@example.com")
                .password("password")
                .fullName("Test User")
                .sex("Male")
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        user.setEmail("updatedemail@example.com");
        user.setPassword("updatedpassword");
        userRepository.save(user);

        Optional<User> updatedUserOptional = userRepository.findById(user.getId());
        assertThat(updatedUserOptional).isPresent();

        User updatedUser = updatedUserOptional.get();
        assertThat(updatedUser.getEmail()).isEqualTo("updatedemail@example.com");
        assertThat(updatedUser.getPassword()).isEqualTo("updatedpassword");
    }

    @Test
    public void testDeleteUser() {

        User user = User.builder()
                .username("testuser")
                .email("testuser@example.com")
                .password("password")
                .fullName("Test User")
                .sex("Male")
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .role(Role.USER)
                .build();

        userRepository.save(user);
        userRepository.delete(user);

        Optional<User> deletedUserOptional = userRepository.findById(user.getId());
        assertThat(deletedUserOptional).isEmpty();
    }

    @Test
    public void testFindById() {
        User user = User.builder()
                .username("testuser")
                .email("testuser@example.com")
                .password("password")
                .fullName("Test User")
                .sex("Male")
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        Optional<User> foundUserOptional = userRepository.findById(user.getId());

        assertThat(foundUserOptional).isPresent();
        User foundUser = foundUserOptional.get();
        assertThat(foundUser.getUsername()).isEqualTo("testuser");
        assertThat(foundUser.getEmail()).isEqualTo("testuser@example.com");
        assertThat(foundUser.getFullName()).isEqualTo("Test User");
    }

    @Test
    public void testFindAllUsers() {
        User user1 = User.builder()
                .username("user1")
                .email("user1@example.com")
                .password("password1")
                .fullName("User One")
                .sex("Female")
                .dateOfBirth(LocalDate.of(1995, 5, 15))
                .role(Role.USER)
                .build();

        User user2 = User.builder()
                .username("user2")
                .email("user2@example.com")
                .password("password2")
                .fullName("User Two")
                .sex("Male")
                .dateOfBirth(LocalDate.of(1985, 10, 30))
                .role(Role.ADMIN)
                .build();

        userRepository.save(user1);
        userRepository.save(user2);

        List<User> allUsers = userRepository.findAll();

        assertThat(allUsers).isNotEmpty();
        assertThat(allUsers).contains(user1, user2);
    }
}
