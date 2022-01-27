package com.corneliu.registration.services;

import com.corneliu.registration.model.entities.UserEntity;

import java.util.Optional;

public interface UserService {

    Optional<UserEntity> findById(long id);

}
