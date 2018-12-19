package com.techplus.connectedinapi.service;

import com.techplus.connectedinapi.model.User;
import com.techplus.connectedinapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    @Override
    public <S extends User> S save(S s) {
        return userRepository.save(s);
    }

}
