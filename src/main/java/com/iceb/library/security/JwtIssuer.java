package com.iceb.library.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtIssuer {
    private final JwtProperties properties;

    public String issue(/* TODO: UUID userId, */String email, List<String> roles) {
        return JWT.create()
                .withSubject(email) // TODO: Use userId instead of email
                .withExpiresAt(Instant.now().plus(Duration.ofDays(1)))
                .withClaim("e", email)
                .withClaim("a", roles)
                .sign(Algorithm.HMAC256(properties.getSecretKey()));
    }
}
