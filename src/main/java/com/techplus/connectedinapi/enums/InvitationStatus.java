package com.techplus.connectedinapi.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum InvitationStatus {

    PENDING("pending"),
    ACCEPTED("accepted"),
    REJECTED("rejected"),
    CANCELED("canceled");

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

    public static InvitationStatus getStatusByValue(String value) {
        InvitationStatus status;
        if ("pending".equals(value)) {
            status = InvitationStatus.PENDING;
        } else if ("accepted".equals(value)) {
            status = InvitationStatus.ACCEPTED;
        } else if ("rejected".equals(value)) {
            status = InvitationStatus.REJECTED;
        } else if ("canceled".equals(value)) {
            status = InvitationStatus.CANCELED;
        } else {
            status = null;
        }
        return status;
    }

}
