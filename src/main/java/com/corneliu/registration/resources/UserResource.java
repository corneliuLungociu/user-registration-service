package com.corneliu.registration.resources;

import com.corneliu.registration.model.converters.UserConverter;
import com.corneliu.registration.model.dto.User;
import com.corneliu.registration.model.entities.UserEntity;
import com.corneliu.registration.services.UserRegistrationService;
import com.corneliu.registration.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserResource {

    @Autowired
    private UserRegistrationService userRegistrationService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{ID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> userDetails(@RequestHeader("auth-token") String authToken
            , @PathVariable(name = "ID") long userId
    ) {
        try {

            boolean isValidToken = userRegistrationService.isValidToken(authToken);
            if (!isValidToken) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            Optional<UserEntity> user = userService.findById(userId);

            return user.map(UserConverter::entityToDto)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
