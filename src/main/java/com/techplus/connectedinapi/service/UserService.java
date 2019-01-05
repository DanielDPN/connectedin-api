package com.techplus.connectedinapi.service;


import com.techplus.connectedinapi.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findById(Long id);

    User findByEmail(String email);

    <S extends User> S save(S s);

    List<User> contactsByUser(Long userId);

    void undoFriendship(Long userId, Long contactId);

    void updatePassword(Long id, String newPassword);

    List<User> findByName(String name, Long userLoggedId);

}
