package com.techplus.connectedinapi.repository;

import com.techplus.connectedinapi.model.Post;
import com.techplus.connectedinapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByOwner(User owner);

    @Override
    <S extends Post> S save(S s);

}
