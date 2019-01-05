package com.techplus.connectedinapi.service;

import com.techplus.connectedinapi.model.Invitation;

public interface InvitationService {

    <S extends Invitation> S save(S s);

}
