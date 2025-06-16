package com.iceb.library.service;

import com.iceb.library.dto.login.LoginResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    LoginResponseDto attemptLogin(String email, String password) throws Exception;
}
