package com.modsen.pizza.security;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class RefreshJWTRequest {

    public String refreshToken;

}