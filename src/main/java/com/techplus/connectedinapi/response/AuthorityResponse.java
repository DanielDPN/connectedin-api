package com.techplus.connectedinapi.response;

public class AuthorityResponse {

    private String authority;

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "AuthorityResponse{" +
                "authority='" + authority + '\'' +
                '}';
    }

}
