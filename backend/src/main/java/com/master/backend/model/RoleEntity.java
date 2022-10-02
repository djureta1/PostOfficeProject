package com.master.backend.model;

import com.master.backend.domain.Permission;
import com.master.backend.domain.Role;
import com.master.backend.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "role_table")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL)
    private List<UserEntity> users = new ArrayList<>();

    //implementirati many to many kao za user entity samo za permisije (join table za permisije ovdje definisati), na permisijama samo listu roleEntity sa mapped by anotacijom
    @ManyToMany
    @JoinTable(
            name = "role_permission",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private List<PermissionEntity> permissions;

    public RoleEntity() {
    }

    public RoleEntity(String name, String description, List<UserEntity> users, List<PermissionEntity> permissions) {
        this.name = name;
        this.description = description;
        this.users = users;
        this.permissions = permissions;
    }

    public RoleEntity(String name, String description, List<PermissionEntity> permissions) {
        this.name = name;
        this.description = description;
        this.permissions = permissions;
    }

    public Role toDomain(){
        List<Permission> permissions = this.permissions.stream()
                .map(entity -> new Permission(entity.getId(), entity.getName(), entity.getDescription()))
                .collect(Collectors.toList());
        return new Role(this.getId(), this.getName(), this.getDescription(), permissions);
    }
}
