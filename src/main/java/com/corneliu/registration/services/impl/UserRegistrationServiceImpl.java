package com.corneliu.registration.services.impl;

import com.corneliu.registration.model.entities.AuthenticationToken;
import com.corneliu.registration.model.entities.UserEntity;
import com.corneliu.registration.repositories.AuthenticationTokenRepository;
import com.corneliu.registration.repositories.UserRepository;
import com.corneliu.registration.services.UserRegistrationService;
import com.corneliu.registration.utils.HashingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationTokenRepository tokenRepository;

    @Override
    @Transactional
    public void registerNewUser(UserEntity user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("User already exists with this email");
        }
        userRepository.save(user);
    }

    @Override
    @Transactional
    public String login(String email, String password) {
        UserEntity user = userRepository.findByEmailAndPassword(email, HashingUtils.hashPassword(password));
        if (user == null) {
            return null;
        }

        AuthenticationToken token = tokenRepository.findByUser(user);
        if (token != null) {
            return token.getToken();
        }

        token = new AuthenticationToken();
        token.setToken(UUID.randomUUID().toString());
        token.setUser(user);

        tokenRepository.save(token);

        return token.getToken();


    }

    @Override
    @Transactional(readOnly = true)
    public boolean isValidToken(String authToken) {
        AuthenticationToken existingToken = tokenRepository.findByToken(authToken);
        return existingToken != null;
    }
}
