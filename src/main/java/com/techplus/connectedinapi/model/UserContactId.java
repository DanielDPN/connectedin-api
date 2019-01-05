package com.techplus.connectedinapi.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserContactId implements Serializable {

    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(name = "contact_id", nullable = false)
    private Long contactId;

    public UserContactId() {
    }

    public UserContactId(Long userId, Long contactId) {
        this.userId = userId;
        this.contactId = contactId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserContactId that = (UserContactId) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(contactId, that.contactId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, contactId);
    }

}
