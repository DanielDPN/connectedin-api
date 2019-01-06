package com.techplus.connectedinapi.repository;

import com.techplus.connectedinapi.model.UserPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPostRepository extends JpaRepository<UserPost, Long> {


}
