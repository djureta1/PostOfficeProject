package com.master.backend.model;

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
@Table(name="user_table")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @NotBlank
    private String email;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<RoleEntity> roles;

    public UserEntity() {
    }

    public UserEntity(String name, String surname, String email, String username, String password, List<RoleEntity> roles) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public User toDomain(){
        List<Role> roles=this.roles.stream()
                .map(entity->new Role(entity.getId(), entity.getName(), entity.getDescription()))
                .collect(Collectors.toList());
        return new User(this.getId(),this.getName(),this.getSurname(),this.getEmail(),this.getUsername(),this.getPassword(), roles);
    }
}
