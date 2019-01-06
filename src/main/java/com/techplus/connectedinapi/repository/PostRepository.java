package com.techplus.connectedinapi.repository;

import com.techplus.connectedinapi.model.Post;
import com.techplus.connectedinapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findById(Long id);

    List<Post> findByOwner(User owner);

    @Override
    <S extends Post> S save(S s);

    @Modifying
    @Override
    void delete(Post post);

    @Modifying
    @Query(value = "update posts set status = :status where id = :id", nativeQuery = true)
    void updatePostStatus(Long id, Long status);

}
