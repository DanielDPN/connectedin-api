package com.techplus.connectedinapi.repository;

import com.techplus.connectedinapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    @Override
    <S extends User> S save(S s);

}