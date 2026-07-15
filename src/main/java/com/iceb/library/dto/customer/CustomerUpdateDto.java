package com.iceb.library.dto.customer;

import com.iceb.library.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerUpdateDto {

    @NotBlank(message = "Name is required and cannot be empty or whitespace.")
    private String name;

    @NotNull(message = "Role is required.")
    private Role role;
}
