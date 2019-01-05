package com.techplus.connectedinapi.service;

import com.techplus.connectedinapi.model.Invitation;
import com.techplus.connectedinapi.repository.InvitationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    @Override
    public List<Invitation> findByReceiver_Id(Long id) {
        return invitationRepository.findByReceiver_Id(id);
    }

    @Override
    public Optional<Invitation> findById(Long id) {
        return invitationRepository.findById(id);
    }

}
