package com.master.backend.controller;

import com.master.backend.domain.Role;
import com.master.backend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping("/api/role")
    List<Role> getAll() { return roleService.getAll(); }

    @GetMapping("/api/role/{id}")
    Role getById(@PathVariable Integer id) throws Exception{
        return roleService.getById(id);
    }

    @PostMapping("/api/role")
    @ResponseStatus(HttpStatus.CREATED)
    Role add(@RequestBody Role role) throws Exception{
        return roleService.addRole(role);
    }

    @PutMapping("/api/role/{id}")
    Role update(@RequestBody Role newRole,@PathVariable Integer id) throws Exception{
        return roleService.update(newRole, id);
    }

    @DeleteMapping("/api/role/{id}")
    void deleteById(@PathVariable Integer id) throws Exception{
        roleService.deleteById(id);
    }
}