package com.modsen.pizza.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshJWTRequest {

    public String refreshToken;

}