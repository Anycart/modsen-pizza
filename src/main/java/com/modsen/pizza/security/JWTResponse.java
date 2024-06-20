package com.modsen.pizza.security;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JWTResponse {
    private String accessToken;
    private String refreshToken;

}
