package com.techplus.connectedinapi.service;

import com.techplus.connectedinapi.model.UserContact;

public interface UserContactService {

    void blockFriendship(Long userId, Long contactId);

    void unblockFriendship(Long userId, Long contactId);

    UserContact blocked(Long userId, Long contactId);

}
