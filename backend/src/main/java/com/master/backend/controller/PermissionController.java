package com.master.backend.controller;

import com.master.backend.domain.Permission;
import com.master.backend.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PermissionController {
    @Autowired
    PermissionService permissionService;

    @GetMapping("/api/permission")
    List<Permission> getAll() { return permissionService.getAll(); }

    @GetMapping("/api/permission/{id}")
    Permission getById(@PathVariable Integer id) throws Exception{
        return permissionService.getById(id);
    }

    @PostMapping("/api/permission")
    @ResponseStatus(HttpStatus.CREATED)
    Permission add(@RequestBody Permission permission) throws Exception{
        return permissionService.addPermission(permission);
    }

    @DeleteMapping("/api/permission/{id}")
    void deleteById(@PathVariable Integer id) throws Exception{
        permissionService.deleteById(id);
    }
}