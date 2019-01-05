package com.techplus.connectedinapi.repository;

import com.techplus.connectedinapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    @Override
    <S extends User> S save(S s);

    @Query(
            value = "select c.id, c.email, c.enabled, c.name, '' as password " +
                    "from users_contacts uc " +
                    "       inner join users c on uc.contact_id = c.id " +
                    "where uc.user_id = :userId",
            nativeQuery = true
    )
    Set<Object[]> contactsByUser(Long userId);

    @Modifying
    @Query(
            value = "delete uc.* " +
                    "from users_contacts uc " +
                    "where uc.user_id = :userId " +
                    "and uc.contact_id = :contactId",
            nativeQuery = true
    )
    void undoFriendship(Long userId, Long contactId);

}
