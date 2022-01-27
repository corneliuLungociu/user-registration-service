package com.corneliu.registration.repositories;

import com.corneliu.registration.model.entities.AuthenticationToken;
import com.corneliu.registration.model.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationTokenRepository extends JpaRepository<AuthenticationToken, Long> {

    AuthenticationToken findByUser(UserEntity user);

    AuthenticationToken findByToken(String token);

}
