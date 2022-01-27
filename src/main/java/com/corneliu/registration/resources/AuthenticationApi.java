package com.corneliu.registration.resources;

import com.corneliu.registration.model.dto.CreateUserRequest;
import com.corneliu.registration.model.dto.LoginRequest;
import com.corneliu.registration.model.dto.LoginResponse;
import com.corneliu.registration.services.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.corneliu.registration.model.converters.UserConverter.requestToEntity;

@RestController
@RequestMapping("/auth")
public class AuthenticationApi {

    @Autowired
    private UserRegistrationService userRegistrationService;

    @PostMapping(value = "/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> signUp(@Valid @RequestBody CreateUserRequest createUserRequest) {
        try {

            userRegistrationService.registerNewUser(requestToEntity(createUserRequest));
            return ResponseEntity.ok().build();

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(value = "/log-in", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {

        try {
            String token = userRegistrationService.login(loginRequest.getEmail(), loginRequest.getPassword());
            if (token != null) {
                return ResponseEntity.ok(new LoginResponse(token));
            }

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
