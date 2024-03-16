package com.kkoksal.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TodoItemRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @JsonProperty(value = "user_id")
    @NotBlank
    private String userId;
}
