package com.techplus.connectedinapi.service;

import com.techplus.connectedinapi.model.User;
import com.techplus.connectedinapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
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

    @Override
    public Set<User> contactsByUser(Long userId) {
        Set<User> response = new HashSet<>();
        Set<Object[]> _response = userRepository.contactsByUser(userId);
        for(Object[] obj: _response) {
            User _user = new User();
            _user.setId(Long.parseLong(obj[0].toString()));
            _user.setEmail(obj[1].toString());
            _user.setEnabled(Boolean.parseBoolean(obj[2].toString()));
            _user.setName(obj[3].toString());
            _user.setPassword(obj[4].toString());

            response.add(_user);
        }
        return response;
    }

    @Override
    @Transactional
    public void undoFriendship(Long userId, Long contactId) {
        userRepository.undoFriendship(userId, contactId);
    }

    @Override
    @Transactional
    public void updatePassword(Long id, String newPassword) {
        userRepository.updatePassword(id, newPassword);
    }
}
