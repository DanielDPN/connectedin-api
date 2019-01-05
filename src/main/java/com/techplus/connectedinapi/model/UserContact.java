package com.techplus.connectedinapi.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users_contacts")
public class UserContact {

    @EmbeddedId
    private UserContactId id;
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private boolean blocked;

    public UserContactId getId() {
        return id;
    }

    public void setId(UserContactId id) {
        this.id = id;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

}
