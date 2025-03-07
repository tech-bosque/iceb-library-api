package com.iceb.library.dto;

import com.iceb.library.enums.Role;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerRequestDto {

    private String name;

    private String email;

    private String password;

    private String phone;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Role> roles = new ArrayList<>();

    public List<String> getRoles() {
        List<String> roleStrings = new ArrayList<>();
        for (Role role : roles) {
            roleStrings.add(role.name());
        }
        return roleStrings;
    }
}