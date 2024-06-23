package com.modsen.pizza.controller;

import com.modsen.pizza.dto.UserDTO;
import com.modsen.pizza.entity.User;
import com.modsen.pizza.security.JWTRequest;
import com.modsen.pizza.security.JWTResponse;
import com.modsen.pizza.security.RefreshJWTRequest;
import com.modsen.pizza.service.impl.AuthServiceImpl;
import com.modsen.pizza.service.impl.RegistrationServiceImpl;
import jakarta.security.auth.message.AuthException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceImpl authServiceImpl;
    private final ModelMapper modelMapper;
    private final RegistrationServiceImpl registrationServiceImpl;

    @PostMapping("login")
    public ResponseEntity<JWTResponse> login(@RequestBody @Valid JWTRequest authRequest) throws AuthException {
        final JWTResponse token = authServiceImpl.login(authRequest);
        return ResponseEntity.ok(token);
    }

    @PostMapping("token")
    public ResponseEntity<JWTResponse> getNewAccessToken(@RequestBody RefreshJWTRequest request) {
        final JWTResponse token = authServiceImpl.getAccessToken(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }

    @PostMapping("refresh")
    public ResponseEntity<JWTResponse> getNewRefreshToken(@RequestBody RefreshJWTRequest request) throws AuthException {
        final JWTResponse token = authServiceImpl.refresh(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }

    @PostMapping("registration")
    public  ResponseEntity<JWTResponse> registration(@RequestBody @Valid UserDTO userDTO){
       return ResponseEntity.ok(registrationServiceImpl.register(modelMapper.map(userDTO, User.class)));
    }

}