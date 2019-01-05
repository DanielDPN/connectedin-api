package com.techplus.connectedinapi.service;

import com.techplus.connectedinapi.model.Post;
import com.techplus.connectedinapi.model.User;

import java.util.List;

public interface PostService {

    List<Post> findByOwner(User owner);

    <S extends Post> S save(S s);

    List<Post> timeline(User user);

}
