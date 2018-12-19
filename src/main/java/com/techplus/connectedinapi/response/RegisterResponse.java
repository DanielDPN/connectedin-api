package com.techplus.connectedinapi.response;

import java.util.Set;

public class RegisterResponse {

    private String email;
    private String name;
    private Set<RoleResponse> roles;
    private Boolean enabled;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<RoleResponse> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleResponse> roles) {
        this.roles = roles;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "RegisterResponse{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", roles=" + roles +
                ", enabled=" + enabled +
                '}';
    }

}
