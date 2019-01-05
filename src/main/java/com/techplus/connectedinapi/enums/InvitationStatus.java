package com.techplus.connectedinapi.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum InvitationStatus {

    PENDING("pending"),
    ACCEPTED("accepted"),
    REJECTED("rejected");

    private String value;

    InvitationStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
