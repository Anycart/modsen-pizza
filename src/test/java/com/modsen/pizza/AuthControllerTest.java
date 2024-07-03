package com.modsen.pizza;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.modsen.pizza.controller.AuthController;
import jakarta.security.auth.message.AuthException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.modsen.pizza.dto.UserDto;
import com.modsen.pizza.entity.User;
import com.modsen.pizza.security.JWTRequest;
import com.modsen.pizza.security.JWTResponse;
import com.modsen.pizza.security.RefreshJWTRequest;
import com.modsen.pizza.service.AuthService;
import com.modsen.pizza.service.RegistrationService;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @InjectMocks
    private AuthController authController;

    @Mock
    private AuthService authService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private RegistrationService registrationService;

    @Test
    public void testLogin() throws AuthException {
        JWTRequest authRequest = new JWTRequest("username", "password");
        JWTResponse mockResponse = new JWTResponse("accessToken", "refreshToken");

        when(authService.login(any(JWTRequest.class))).thenReturn(mockResponse);

        ResponseEntity<JWTResponse> response = authController.login(authRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockResponse, response.getBody());
    }

    @Test
    public void testGetNewAccessToken() {
        RefreshJWTRequest request = new RefreshJWTRequest();
        request.refreshToken = "refreshToken";
        JWTResponse mockResponse = new JWTResponse("newAccessToken", "refreshToken");

        when(authService.getAccessToken(anyString())).thenReturn(mockResponse);

        ResponseEntity<JWTResponse> response = authController.getNewAccessToken(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockResponse, response.getBody());
    }

    @Test
    public void testGetNewRefreshToken() throws AuthException {
        RefreshJWTRequest request = new RefreshJWTRequest();
        request.refreshToken = "refreshToken";
        JWTResponse mockResponse = new JWTResponse("accessToken", "newRefreshToken");

        when(authService.refresh(anyString())).thenReturn(mockResponse);

        ResponseEntity<JWTResponse> response = authController.getNewRefreshToken(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockResponse, response.getBody());
    }

    @Test
    public void testRegistration() {
        UserDto userDto = new UserDto();
        userDto.setUsername("username");
        userDto.setEmail("email@example.com");
        userDto.setPassword("password");
        userDto.setFullName("Full Name");
        userDto.setSex("M");
        userDto.setLocalDate(LocalDate.now());

        User mockUser = new User();
        mockUser.setUsername("username");
        mockUser.setEmail("email@example.com");
        mockUser.setPassword("password");
        mockUser.setFullName("Full Name");
        mockUser.setSex("M");
        mockUser.setDateOfBirth(LocalDate.now());

        JWTResponse mockResponse = new JWTResponse("accessToken", "refreshToken");

        when(modelMapper.map(any(UserDto.class), any(Class.class))).thenReturn(mockUser);
        when(registrationService.register(any(User.class))).thenReturn(mockResponse);

        ResponseEntity<JWTResponse> response = authController.registration(userDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockResponse, response.getBody());
    }
}
