package com.iceb.library.service.impl;


import com.iceb.library.dto.RolesResponseDto;
import com.iceb.library.enums.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class RolesServiceImplTest {

    @InjectMocks
    private RolesServiceImpl rolesServiceImpl;

    @Test
    void getRolesTest() {
        RolesResponseDto rolesResponseDto = rolesServiceImpl.getRoles();

        assertNotNull(rolesResponseDto);
        assertNotNull(rolesResponseDto.getRoles());
        assertEquals(Role.values().length, rolesResponseDto.getRoles().size());
        assertThat(Role.values()).usingRecursiveComparison().isEqualTo(rolesResponseDto);

    }

}
