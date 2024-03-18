package com.kkoksal.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class TodoResponsePageable {
    @Schema(name = "todoItems", example = "[]", requiredMode = Schema.RequiredMode.REQUIRED)
    List<TodoResponse> todoItems;

    @Schema(name = "total", example = "10", requiredMode = Schema.RequiredMode.REQUIRED)
    Long total;
}
