package com.iceb.library.dto.customer;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerPasswordUpdateDto {

    @NotBlank(message = "Current password is required and cannot be empty or whitespace.")
    private String oldPassword;

    @NotBlank(message = "New password is required and cannot be empty or whitespace.")
    private String newPassword;
}
