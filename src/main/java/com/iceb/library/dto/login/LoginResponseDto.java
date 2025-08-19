package com.iceb.library.dto.login;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponseDto {
    private final String accessToken;
}
