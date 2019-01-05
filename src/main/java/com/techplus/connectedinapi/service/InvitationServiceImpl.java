package com.techplus.connectedinapi.service;

import com.techplus.connectedinapi.model.Invitation;
import com.techplus.connectedinapi.repository.InvitationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class InvitationServiceImpl implements InvitationService {

    private final InvitationRepository invitationRepository;

    @Autowired
    public InvitationServiceImpl(InvitationRepository invitationRepository) {
        this.invitationRepository = invitationRepository;
    }

    @Override
    @Transactional
    public <S extends Invitation> S save(S s) {
        return invitationRepository.save(s);
    }

}
