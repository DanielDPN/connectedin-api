package com.techplus.connectedinapi.service;

import com.techplus.connectedinapi.enums.PostStatus;
import com.techplus.connectedinapi.model.Post;
import com.techplus.connectedinapi.model.User;
import com.techplus.connectedinapi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public List<Post> findByOwner(User owner) {
        return postRepository.findByOwner(owner);
    }

    @Override
    @Transactional
    public <S extends Post> S save(S s) {
        return postRepository.save(s);
    }

    @Override
    public List<Post> timeline(User user) {
        List<Post> _myPost = postRepository.findByOwner(user);
        List<Post> response = new ArrayList<>(_myPost);
        for (User contact: userService.contactsByUser(user.getId())) {
            response.addAll(postRepository.findByOwner(contact));
        }
        response.sort(Post::compareTo);
        return response;
    }

    @Override
    @Transactional
    public void delete(Post post) {
        postRepository.delete(post);
    }

    @Override
    @Transactional
    public void updatePostStatus(Long id, Long status) {
        postRepository.updatePostStatus(id, status);
    }

}
