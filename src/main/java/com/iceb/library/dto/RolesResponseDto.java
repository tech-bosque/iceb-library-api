package com.iceb.library.dto;

import com.iceb.library.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RolesResponseDto {

    private List<Role> roles;

}
