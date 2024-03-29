package com.kkoksal.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TodoItemRequest {

    @NotBlank(message = "Item title can not be empty")
    @Schema(name = "title", example = "example title", requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;

    @NotBlank(message = "Item content can not be empty")
    @Schema(name = "content", example = "description", requiredMode = Schema.RequiredMode.REQUIRED)
    private String content;
}
