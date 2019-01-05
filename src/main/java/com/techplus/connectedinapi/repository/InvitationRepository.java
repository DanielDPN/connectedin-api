package com.techplus.connectedinapi.repository;

import com.techplus.connectedinapi.model.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Long> {

    @Override
    <S extends Invitation> S save(S s);

}
