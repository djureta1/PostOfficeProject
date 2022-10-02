package com.master.backend.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class Permission {
    private Integer id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    private List<Role> roles;

    public Permission() {
    }

    public Permission(Integer id, String name, String description, List<Role> roles) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.roles = roles;
    }

    public Permission(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
