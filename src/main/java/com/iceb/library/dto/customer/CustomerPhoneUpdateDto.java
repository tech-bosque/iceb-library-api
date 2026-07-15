package com.iceb.library.dto.customer;

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
public class CustomerPhoneUpdateDto {

    @NotBlank(message = "Phone is required and cannot be empty or whitespace.")
    @Pattern(regexp = "^[^*]+$", message = "Phone must not contain masked characters.")
    private String phone;
}
