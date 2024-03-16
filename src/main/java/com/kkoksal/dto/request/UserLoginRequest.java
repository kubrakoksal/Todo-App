package com.kkoksal.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequest {

    @NotBlank
    @JsonProperty("user_name")
    private String userName;

    @NotBlank
    private String password;

}
