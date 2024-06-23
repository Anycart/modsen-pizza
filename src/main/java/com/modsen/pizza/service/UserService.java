package com.modsen.pizza.service;

import com.modsen.pizza.entity.User;

public interface UserService {
    User findByUsername(String username);
}
