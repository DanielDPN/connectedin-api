package com.techplus.connectedinapi.service;

import com.techplus.connectedinapi.model.Post;
import com.techplus.connectedinapi.model.User;

import java.util.List;
import java.util.Optional;

public interface PostService {

    Optional<Post> findById(Long id);

    List<Post> findByOwner(User owner);

    <S extends Post> S save(S s);

    List<Post> timeline(User user);

    void delete(Post post);

}
