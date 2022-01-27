package com.corneliu.registration.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @NotBlank(message = "email is mandatory")
    @Email(message = "The email format is not valid")
    private String email;

    @NotBlank(message = "Password is mandatory")
    private String password;

}
