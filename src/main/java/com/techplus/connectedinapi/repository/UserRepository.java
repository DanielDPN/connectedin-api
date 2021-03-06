package com.techplus.connectedinapi.repository;

import com.techplus.connectedinapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long id);

    User findByEmail(String email);

    @Override
    <S extends User> S save(S s);

    @Query(
            value = "select c.id, c.email, c.enabled, c.name, '' as password, c.active " +
                    "from users_contacts uc " +
                    "       inner join users c on uc.contact_id = c.id " +
                    "where uc.user_id = :userId",
            nativeQuery = true
    )
    List<Object[]> contactsByUser(Long userId);

    @Query(
            value = "select u.id, u.email, u.enabled, u.name, '' as password, u.active " +
                    "from users u " +
                    "where u.id not in (select uc.contact_id from users_contacts uc where uc.user_id = :userId) " +
                    "  and u.id <> :userId " +
                    "order by rand()" +
                    "limit 4",
            nativeQuery = true
    )
    List<Object[]> friendshipSuggestions(Long userId);

    @Query(
            value = "select c.id, c.email, c.enabled, c.name, '' as password, c.active " +
                    "from users c",
            nativeQuery = true
    )
    List<Object[]> users();

    @Modifying
    @Query(
            value = "delete uc.* " +
                    "from users_contacts uc " +
                    "where uc.user_id = :userId " +
                    "and uc.contact_id = :contactId",
            nativeQuery = true
    )
    void undoFriendship(Long userId, Long contactId);

    @Modifying
    @Query(
            value = "update users " +
                    "set password = :newPassword " +
                    "where id = :id",
            nativeQuery = true
    )
    void updatePassword(Long id, String newPassword);

    @Query(
            value = "select c.id, c.email, c.enabled, c.name, '' as password " +
                    "from users c " +
                    "where c.name like :name " +
                    "and c.id <> :userLoggedId",
            nativeQuery = true
    )
    List<Object[]> findByName(String name, Long userLoggedId);

}
