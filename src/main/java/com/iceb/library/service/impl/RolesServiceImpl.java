package com.iceb.library.service.impl;

import com.iceb.library.dto.RolesResponseDto;
import com.iceb.library.enums.Role;
import com.iceb.library.service.RolesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class RolesServiceImpl implements RolesService {

    private static final Logger logger = LoggerFactory.getLogger(RolesServiceImpl.class);

    @Override
    public RolesResponseDto getRoles() {
        logger.info("Fetching roles");
        logger.debug("Fetching roles");

        RolesResponseDto rolesResponseDto = RolesResponseDto.builder()
                .roles(Arrays.asList(Role.values()))
                .build();

        logger.debug("Fetched roles: {}", rolesResponseDto);
        logger.info("Fetched roles");
        return rolesResponseDto;
    }

}
