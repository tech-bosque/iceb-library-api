package com.iceb.library.dto.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerEmailUpdateDto {

    @NotBlank(message = "Current email is required and cannot be empty or whitespace.")
    @Email(message = "Current email must be a valid email address.")
    @Pattern(regexp = "^[^*]+$", message = "Current email must not contain masked characters.")
    private String oldEmail;

    @NotBlank(message = "New email is required and cannot be empty or whitespace.")
    @Email(message = "New email must be a valid email address.")
    @Pattern(regexp = "^[^*]+$", message = "New email must not contain masked characters.")
    private String newEmail;
}
