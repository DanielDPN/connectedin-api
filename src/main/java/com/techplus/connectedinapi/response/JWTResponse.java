package com.techplus.connectedinapi.response;

import java.util.List;

public class JWTResponse {

    private String sub;
    private List<AuthorityResponse> roles;
    private Integer exp;

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public List<AuthorityResponse> getRoles() {
        return roles;
    }

    public void setRoles(List<AuthorityResponse> roles) {
        this.roles = roles;
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "JWTResponse{" +
                "sub='" + sub + '\'' +
                ", roles=" + roles +
                ", exp=" + exp +
                '}';
    }

}
