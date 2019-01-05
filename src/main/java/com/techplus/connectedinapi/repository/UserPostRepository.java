package com.techplus.connectedinapi.repository;

import com.techplus.connectedinapi.model.UserPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPostRepository extends JpaRepository<UserPost, Long> {

    @Modifying
    @Query(
            value = "delete up.* " +
                    "from users_posts up " +
                    "where up.user_id = :userId and up.post_id = :postId",
            nativeQuery = true
    )
    void userPostRemove(Long userId, Long postId);

}
