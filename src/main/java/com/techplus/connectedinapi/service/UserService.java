package com.techplus.connectedinapi.service;


import com.techplus.connectedinapi.model.User;

import java.util.Set;

public interface UserService {

    User findByEmail(String email);

    <S extends User> S save(S s);

    Set<User> contactsByUser(Long userId);

    void undoFriendship(Long userId, Long contactId);

}
