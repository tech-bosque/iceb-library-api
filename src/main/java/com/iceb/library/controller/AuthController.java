package com.iceb.library.controller;


import com.iceb.library.dto.login.LoginRequestDto;
import com.iceb.library.dto.login.LoginResponseDto;
import com.iceb.library.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request) throws Exception {
        LoginResponseDto response = authService.attemptLogin(request.getEmail(), request.getPassword());

        return ResponseEntity.ok(response);
    }
}
