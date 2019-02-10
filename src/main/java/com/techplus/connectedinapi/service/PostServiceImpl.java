package com.techplus.connectedinapi.service;

import com.techplus.connectedinapi.enums.PostStatus;
import com.techplus.connectedinapi.model.Like;
import com.techplus.connectedinapi.model.Post;
import com.techplus.connectedinapi.model.User;
import com.techplus.connectedinapi.model.UserContact;
import com.techplus.connectedinapi.repository.LikeRepository;
import com.techplus.connectedinapi.repository.PostRepository;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserService userService;
    private final UserContactService userContactService;
    private final LikeRepository likeRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserService userService, UserContactService userContactService, LikeRepository likeRepository) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.userContactService = userContactService;
        this.likeRepository = likeRepository;
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public List<Post> findByOwner(User owner) {
        return postRepository.findByOwnerAndStatus(owner, PostStatus.CREATED);
    }

    @Override
    @Transactional
    public <S extends Post> S save(S s) {
        return postRepository.save(s);
    }

    @Override
    public List<Post> timeline(User user) {
        List<Post> _myPost = postRepository.findByOwnerAndStatus(user, PostStatus.CREATED);
        List<Post> response = new ArrayList<>(_myPost);
        for (User contact: userService.contactsByUser(user.getId())) {
            UserContact userContact = userContactService.blocked(contact.getId(), user.getId());
            if(!userContact.isBlocked()){
                response.addAll(postRepository.findByOwnerAndStatus(contact, PostStatus.CREATED));
            }
        }
        for (Post post: response) {
            post.setLikes(likeRepository.findAllByPostId(post.getId()));
            for (Like like: post.getLikes()) {
                if (user.getId().equals(like.getUserId())) {
                    post.setLiked(true);
                }
            }
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
