package com.modsen.pizza.security;

import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import com.modsen.pizza.enumeration.Role;
import org.springframework.stereotype.Component;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Component
public final class JWTUtils {

    public static JWTAuthentication generate(Claims claims) {
        final JWTAuthentication jwtInfoToken = new JWTAuthentication();
        jwtInfoToken.setRoles(getRoles(claims));
        jwtInfoToken.setFirstName(claims.get("firstName", String.class));
        jwtInfoToken.setUsername(claims.getSubject());
        return jwtInfoToken;
    }

    private static Set<Role> getRoles(Claims claims) {
        final String rolesString = claims.get("roles", String.class);
        final List<String> roles = Arrays.asList(rolesString.split(","));
        return roles.stream()
                .map(Role::valueOf)
                .collect(Collectors.toSet());
    }

}
