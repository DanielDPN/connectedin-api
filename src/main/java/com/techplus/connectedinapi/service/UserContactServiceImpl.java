package com.techplus.connectedinapi.service;

import com.techplus.connectedinapi.repository.UserContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserContactServiceImpl implements UserContactService {

    private final UserContactRepository userContactRepository;

    @Autowired
    public UserContactServiceImpl(UserContactRepository userContactRepository) {
        this.userContactRepository = userContactRepository;
    }

    @Override
    @Transactional
    public void blockFriendship(Long userId, Long contactId) {
        userContactRepository.blockFriendship(userId, contactId);
    }

}
