package com.corneliu.registration.model.converters;

import com.corneliu.registration.model.dto.CreateUserRequest;
import com.corneliu.registration.model.dto.User;
import com.corneliu.registration.model.entities.UserEntity;
import com.corneliu.registration.utils.HashingUtils;

public class UserConverter {

    private UserConverter() {
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }

    public static UserEntity requestToEntity(CreateUserRequest createUserRequest) {
        if (createUserRequest == null) {
            return null;
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(createUserRequest.getEmail());
        userEntity.setName(createUserRequest.getName());

        String password = HashingUtils.hashPassword(createUserRequest.getPassword());
        userEntity.setPassword(password);

        return userEntity;
    }



    public static User entityToDto(UserEntity entity) {
        if (entity == null) {
            return null;
        }

        return new User(entity.getId(), entity.getName(), entity.getEmail());
    }
}
