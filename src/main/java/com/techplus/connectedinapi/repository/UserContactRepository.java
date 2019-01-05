package com.techplus.connectedinapi.repository;

import com.techplus.connectedinapi.model.UserContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserContactRepository extends JpaRepository<UserContact, Long> {

    @Modifying
    @Query(
            value = "update users_contacts " +
                    "set blocked = true " +
                    "where user_id = :userId and contact_id = :contactId",
            nativeQuery = true
    )
    void blockFriendship(Long userId, Long contactId);

}
