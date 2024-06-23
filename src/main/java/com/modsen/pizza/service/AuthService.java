package com.modsen.pizza.service;

import com.modsen.pizza.security.JWTAuthentication;
import com.modsen.pizza.security.JWTRequest;
import com.modsen.pizza.security.JWTResponse;
import jakarta.security.auth.message.AuthException;
import lombok.NonNull;

public interface AuthService {
    JWTResponse login(@NonNull JWTRequest authRequest) throws AuthException;

    JWTResponse getAccessToken(@NonNull String refreshToken);

    JWTResponse refresh(@NonNull String refreshToken) throws AuthException;

}
