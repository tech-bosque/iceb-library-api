package com.iceb.library.service.impl;

import com.iceb.library.enums.Role;
import com.iceb.library.service.RolesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RolesServiceImpl implements RolesService {

    private static final Logger logger = LoggerFactory.getLogger(RolesServiceImpl.class);
   
    @Override
    public List<String> listRoles() {
        logger.info("Fetching roles");
        logger.debug("Fetching roles");

        List<String> responseDtos = Arrays.stream(Role.values())
            .map(Enum::name)
            .collect(Collectors.toList());

        logger.debug("Fetched roles: {}", responseDtos);
        logger.info("Fetched roles");
        return responseDtos;
    }

}
