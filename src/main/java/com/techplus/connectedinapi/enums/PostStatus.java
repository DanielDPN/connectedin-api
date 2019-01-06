package com.techplus.connectedinapi.enums;

public enum PostStatus {

    CREATED("created"),
    DELETED("deleted");

    private String value;

    PostStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static PostStatus getStatusByValue(String value) {
        PostStatus status;
        if ("created".equals(value)) {
            status = PostStatus.CREATED;
        } else if ("deleted".equals(value)) {
            status = PostStatus.DELETED;
        } else {
            status = null;
        }
        return status;
    }

}
