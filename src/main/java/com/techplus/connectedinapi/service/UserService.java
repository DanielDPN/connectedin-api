package com.techplus.connectedinapi.service;


import com.techplus.connectedinapi.model.User;

import java.util.List;

public interface UserService {

    User findByEmail(String email);

    <S extends User> S save(S s);

    List<User> contactsByUser(Long userId);

}
