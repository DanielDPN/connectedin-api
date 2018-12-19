package com.techplus.connectedinapi.service;


import com.techplus.connectedinapi.model.User;

public interface UserService {

    User findByEmail(String email);

    <S extends User> S save(S s);

}
