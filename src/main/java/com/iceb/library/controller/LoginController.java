package com.iceb.library.controller;

import com.iceb.library.dto.LoginRequestDTO;
import com.iceb.library.dto.LoginResponseDTO;
import com.iceb.library.security.JwtIssuer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class LoginController {
    private final JwtIssuer jwtIssuer;

    @PostMapping
    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO) {
        var token = jwtIssuer.issue(1L, loginRequestDTO.getEmail(), List.of("USER"));
        return LoginResponseDTO
                .builder()
                .accessToken(token)
                .build();
    }
}
