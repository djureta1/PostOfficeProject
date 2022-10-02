package com.master.backend.model;

import com.master.backend.domain.Permission;
import com.master.backend.domain.Role;
import com.master.backend.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "permission")
public class PermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @ManyToMany(mappedBy = "permissions", cascade = CascadeType.REMOVE)
    private List<RoleEntity> roles;

    public PermissionEntity() {
    }

    public PermissionEntity(String name, String description, List<RoleEntity> roles) {
        this.name = name;
        this.description = description;
        this.roles = roles;
    }

    public PermissionEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Permission toDomain(){
        return new Permission(this.getId(),this.getName(),this.getDescription());
    }
}
