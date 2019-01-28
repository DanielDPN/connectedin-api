package com.techplus.connectedinapi.repository;

import com.techplus.connectedinapi.model.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Long> {

    @Override
    <S extends Invitation> S save(S s);

    List<Invitation> findByReceiver_Id(Long id);

    List<Invitation> findBySender_Id(Long id);

    Optional<Invitation> findById(Long id);

}
