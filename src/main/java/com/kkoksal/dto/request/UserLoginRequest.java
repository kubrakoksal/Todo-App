package com.kkoksal.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserLoginRequest {

    @Email
    private String email;

    @NotBlank
    private String password;

}
