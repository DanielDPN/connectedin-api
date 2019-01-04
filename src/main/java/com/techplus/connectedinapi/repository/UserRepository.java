package com.techplus.connectedinapi.repository;

import com.techplus.connectedinapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    List<Object[]> contactsByUser(Long userId);

}
