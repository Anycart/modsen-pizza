package com.modsen.pizza.service;

import com.modsen.pizza.enumeration.Role;
import com.modsen.pizza.model.User;
import com.modsen.pizza.repository.UserRepository;
import com.modsen.pizza.security.JWTResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow();
    }

    @Transactional
    public JWTResponse register(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole(Role.USER);
        final String accessToken = jwtService.generateAccessToken(user);
        final String refreshToken = jwtService.generateRefreshToken(user);
        userRepository.save(user);
        return new JWTResponse(accessToken, refreshToken);
    }

}
