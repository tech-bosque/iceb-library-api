package com.iceb.library.controller;

import com.iceb.library.dto.LoginRequestDTO;
import com.iceb.library.dto.LoginResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class LoginController {

    @PostMapping
    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO) {

    }
}
