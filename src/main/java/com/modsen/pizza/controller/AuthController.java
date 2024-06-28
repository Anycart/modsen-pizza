package com.modsen.pizza.controller;

import com.modsen.pizza.dto.UserDto;
import com.modsen.pizza.entity.User;
import com.modsen.pizza.security.JWTRequest;
import com.modsen.pizza.security.JWTResponse;
import com.modsen.pizza.security.RefreshJWTRequest;
import com.modsen.pizza.service.AuthService;
import com.modsen.pizza.service.RegistrationService;
import jakarta.security.auth.message.AuthException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final AuthService authService;
    private final ModelMapper modelMapper;
    private final RegistrationService registrationService;

    @PostMapping("/login")
    public ResponseEntity<JWTResponse> login(@RequestBody @Valid JWTRequest authRequest) throws AuthException {
        final JWTResponse token = authService.login(authRequest);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/token")
    public ResponseEntity<JWTResponse> getNewAccessToken(@RequestBody RefreshJWTRequest request) {
        final JWTResponse token = authService.getAccessToken(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/refresh")
    public ResponseEntity<JWTResponse> getNewRefreshToken(@RequestBody RefreshJWTRequest request) throws AuthException {
        final JWTResponse token = authService.refresh(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/registration")
    public ResponseEntity<JWTResponse> registration(@RequestBody @Valid UserDto userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        JWTResponse register = registrationService.register(user);
        return ResponseEntity.ok(register);
    }
}