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

    @Query("SELECT CASE WHEN COUNT(uc) > 0 THEN true ELSE false END FROM UserContact uc WHERE uc.id.userId = :userId and uc.id.contactId = :contactId")
    boolean existsFriendship(Long userId, Long contactId);

    @Query(
            value = "select blocked " +
                    "from users_contacts " +
                    "where user_id = :userId and contact_id = :contactId",
            nativeQuery = true
    )
    boolean blocked(Long userId, Long contactId);

}
