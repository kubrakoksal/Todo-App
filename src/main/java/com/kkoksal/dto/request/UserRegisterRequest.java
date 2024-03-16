package com.kkoksal.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRegisterRequest {

    @NotBlank
    @JsonProperty(value = "first_name")
    private String firstName;

    @NotBlank
    @JsonProperty(value = "last_name")
    private String lastName;

    @NotBlank
    @JsonProperty(value = "user_name")
    private String userName;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

}
