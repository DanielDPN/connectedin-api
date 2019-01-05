package com.techplus.connectedinapi.service;

import com.techplus.connectedinapi.model.Invitation;

import java.util.List;

public interface InvitationService {

    <S extends Invitation> S save(S s);

    List<Invitation> findByReceiver_Id(Long id);

}
