package com.iceb.library.controller;

import com.iceb.library.dto.login.LoginRequestDto;
import com.iceb.library.dto.login.LoginResponseDto;
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
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDTO) {
        var token = jwtIssuer.issue(loginRequestDTO.getEmail(), List.of("USER"));
        return LoginResponseDto
                .builder()
                .accessToken(token)
                .build();
    }
}
