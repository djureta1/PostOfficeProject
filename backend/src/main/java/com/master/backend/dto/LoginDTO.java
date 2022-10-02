package com.master.backend.dto;

import com.master.backend.domain.Role;

import java.util.List;

public class LoginDTO {

    public String token;

    public List<Role> roles;

    public LoginDTO() {
    }

    public LoginDTO(String token, List<Role> roles) {
        this.token = token;
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
