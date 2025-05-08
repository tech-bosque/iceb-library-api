package com.iceb.library.dto.customer;

import com.iceb.library.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponseDto {

    private UUID id;
    private String name;
    private String email;
    private String phone;
    private Role role;
    private Boolean archived;

}
