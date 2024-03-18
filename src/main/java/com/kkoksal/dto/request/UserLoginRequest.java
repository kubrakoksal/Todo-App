package com.kkoksal.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequest {

    @NotBlank(message = "Username can not be empty")
    @JsonProperty("user_name")
    @Schema(name = "userName", example = "kkoksal", requiredMode = Schema.RequiredMode.REQUIRED)
    private String userName;

    @NotBlank(message = "Password can not be empty")
    @Schema(name = "password", example = "159", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

}
