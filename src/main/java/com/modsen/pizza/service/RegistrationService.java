package com.modsen.pizza.service;

import com.modsen.pizza.entity.User;
import com.modsen.pizza.security.JWTResponse;

public interface RegistrationService {
    JWTResponse register(User user);
}
