package com.kkoksal.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TodoResponse {
    @Schema(name = "id", example = "a9e023a8-45d...", requiredMode = Schema.RequiredMode.REQUIRED)
    private String id;

    @Schema(name = "title", example = "example title", requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;

    @Schema(name = "content", example = "description", requiredMode = Schema.RequiredMode.REQUIRED)
    private String content;

    @Schema(name = "todo item status", example = "true/false", requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean completed;
}
