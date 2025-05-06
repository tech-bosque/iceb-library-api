package com.iceb.library.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponseDTO {
    private final String accessToken;
}
