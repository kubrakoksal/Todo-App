package com.kkoksal.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRegisterRequest {

    @NotBlank(message = "First Name can not be empty")
    @JsonProperty(value = "first_name")
    @Schema(name = "firstName", example = "name", requiredMode = Schema.RequiredMode.REQUIRED)
    private String firstName;

    @NotBlank(message = "Last name can not be empty")
    @JsonProperty(value = "last_name")
    @Schema(name = "lastName", example = "Surname", requiredMode = Schema.RequiredMode.REQUIRED)
    private String lastName;

    @NotBlank(message = "User name can nor be empty")
    @JsonProperty(value = "user_name")
    @Schema(name = "userName", example = "user1", requiredMode = Schema.RequiredMode.REQUIRED)
    private String userName;

    @NotBlank(message = "Email can not be empty")
    @Email(message = "Email format is incorrect")
    @Schema(name = "email", example = "user1@company.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @NotBlank(message = "Password can not be empty")
    @Schema(name = "password", example = "1234", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

}
