package com.techplus.connectedinapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PasswordUpdate {

    @JsonProperty(value = "old_password")
    private String oldPassword;
    @JsonProperty(value = "new_password")
    private String newPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

}
