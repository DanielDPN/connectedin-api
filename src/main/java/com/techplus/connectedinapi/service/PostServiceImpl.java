package com.techplus.connectedinapi.service;

import com.techplus.connectedinapi.model.Post;
import com.techplus.connectedinapi.model.User;
import com.techplus.connectedinapi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional(readOnly = true)
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Set<Post> findByOwner(User owner) {
        return postRepository.findByOwner(owner);
    }

    @Override
    @Transactional
    public <S extends Post> S save(S s) {
        return postRepository.save(s);
    }

}
