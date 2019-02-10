package com.techplus.connectedinapi.service;

import com.techplus.connectedinapi.model.User;
import com.techplus.connectedinapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    public List<User> contactsByUser(Long userId) {
        List<User> response = new ArrayList<>();
        List<Object[]> _response = userRepository.contactsByUser(userId);
        for(Object[] obj: _response) {
            User _user = new User();
            _user.setId(Long.parseLong(obj[0].toString()));
            _user.setEmail(obj[1].toString());
            _user.setEnabled(Boolean.parseBoolean(obj[2].toString()));
            _user.setName(obj[3].toString());
            _user.setPassword(obj[4].toString());
            _user.setActive(Boolean.parseBoolean(obj[5].toString()));

            response.add(_user);
        }
        return response;
    }

    @Override
    public List<User> friendshipSuggestions(Long userId) {
        List<User> response = new ArrayList<>();
        List<Object[]> _response = userRepository.friendshipSuggestions(userId);
        for(Object[] obj: _response) {
            User _user = new User();
            _user.setId(Long.parseLong(obj[0].toString()));
            _user.setEmail(obj[1].toString());
            _user.setEnabled(Boolean.parseBoolean(obj[2].toString()));
            _user.setName(obj[3].toString());
            _user.setPassword(obj[4].toString());
            _user.setActive(Boolean.parseBoolean(obj[5].toString()));

            response.add(_user);
        }
        return response;
    }

    @Override
    public List<User> users() {
        List<User> response = new ArrayList<>();
        List<Object[]> _response = userRepository.users();
        for(Object[] obj: _response) {
            User _user = new User();
            _user.setId(Long.parseLong(obj[0].toString()));
            _user.setEmail(obj[1].toString());
            _user.setEnabled(Boolean.parseBoolean(obj[2].toString()));
            _user.setName(obj[3].toString());
            _user.setPassword(obj[4].toString());
            _user.setActive(Boolean.parseBoolean(obj[5].toString()));

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

    @Override
    public List<User> findByName(String name, Long userLoggedId) {
        name = name != null ? "%" + name + "%" : "%%";
        List<User> response = new ArrayList<>();
        List<Object[]> _response = userRepository.findByName(name, userLoggedId);
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

}
