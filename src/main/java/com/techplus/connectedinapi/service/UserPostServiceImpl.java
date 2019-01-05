package com.techplus.connectedinapi.service;

import com.techplus.connectedinapi.repository.UserPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserPostServiceImpl implements UserPostService {

    private final UserPostRepository userPostRepository;

    @Autowired
    public UserPostServiceImpl(UserPostRepository userPostRepository) {
        this.userPostRepository = userPostRepository;
    }

    @Override
    @Transactional
    public void userPostRemove(Long userId, Long postId) {
        userPostRepository.userPostRemove(userId, postId);
    }

}
