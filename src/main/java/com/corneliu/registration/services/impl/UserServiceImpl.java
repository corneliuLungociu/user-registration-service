package com.corneliu.registration.services.impl;

import com.corneliu.registration.model.entities.UserEntity;
import com.corneliu.registration.repositories.UserRepository;
import com.corneliu.registration.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<UserEntity> findById(long id) {
        return userRepository.findById(id);
    }


}
