package com.kkoksal.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RegisterResponse {

    @Schema(name = "id", example = "a9e023a8-45d...", requiredMode = Schema.RequiredMode.REQUIRED)
    private String id;

    @Schema(name = "username", example = "user_1", requiredMode = Schema.RequiredMode.REQUIRED)
    private String userName;

    @Schema(name = "email", example = "user_1@company.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

}
