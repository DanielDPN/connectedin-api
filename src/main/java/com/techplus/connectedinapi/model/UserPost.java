package com.techplus.connectedinapi.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users_posts")
public class UserPost {

    @EmbeddedId
    private UserPostId id;

    public UserPostId getId() {
        return id;
    }

    public void setId(UserPostId id) {
        this.id = id;
    }

}
