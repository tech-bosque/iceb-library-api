package com.iceb.library.controller;

import com.iceb.library.service.RolesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@Tag(name = "Roles list", description = "APIs for get Roles")
public class RolesController {

    @Autowired
    private RolesService rolesService;

    @GetMapping
    @Operation(summary = "Get all roles")
    public ResponseEntity<List<String>> listRoles() {
        List<String> roles = rolesService.listRoles();
        return ResponseEntity.ok(roles);
    }
}
