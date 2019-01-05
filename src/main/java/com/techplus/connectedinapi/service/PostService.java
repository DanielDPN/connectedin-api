package com.techplus.connectedinapi.service;

import com.techplus.connectedinapi.model.Post;
import com.techplus.connectedinapi.model.User;

import java.util.Set;

public interface PostService {

    Set<Post> findByOwner(User owner);

}
