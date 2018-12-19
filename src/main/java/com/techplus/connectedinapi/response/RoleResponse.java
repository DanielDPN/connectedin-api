package com.techplus.connectedinapi.response;

public class RoleResponse {

    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "RoleResponse{" +
                "role='" + role + '\'' +
                '}';
    }

}
