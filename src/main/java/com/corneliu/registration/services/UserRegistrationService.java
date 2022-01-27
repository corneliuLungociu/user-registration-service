package com.corneliu.registration.services;

import com.corneliu.registration.model.entities.UserEntity;

public interface UserRegistrationService {

    void registerNewUser(UserEntity user);

    String login(String email, String password);

    boolean isValidToken(String authToken);
}
