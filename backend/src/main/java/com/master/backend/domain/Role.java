package com.master.backend.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class Role {
    private Integer id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    private List<User> users;

    private List<Permission> permissions;

    public Role() {
    }

    public Role(Integer id, String name, String description, List<User> users, List<Permission> permissions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.users = users;
        this.permissions = permissions;
    }

    public Role(Integer id, String name, String description, List<Permission> permissions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.permissions = permissions;
    }

    public Role(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
