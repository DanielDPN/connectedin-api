package com.techplus.connectedinapi.repository;

import com.techplus.connectedinapi.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    @Override
    <S extends Like> S save(S s);

}
