package com.iceb.library.dto.customer;

import com.iceb.library.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerRequestDto {

    private String name;

    private String email;

    private String password;

    private String phone;

    private Role role;

}
