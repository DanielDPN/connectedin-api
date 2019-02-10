package com.techplus.connectedinapi.service;

import com.techplus.connectedinapi.model.Like;

public interface LikeService {

    <S extends Like> S save(S s);

}
