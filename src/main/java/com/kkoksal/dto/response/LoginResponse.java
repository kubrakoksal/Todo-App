package com.kkoksal.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {

    @Schema(name = "token", example = "eyJ0eXAi...", requiredMode = Schema.RequiredMode.REQUIRED)
    private String token;

    @JsonProperty("user_name")
    @Schema(name = "username", example = "user_1", requiredMode = Schema.RequiredMode.REQUIRED)
    private String userName;

}
