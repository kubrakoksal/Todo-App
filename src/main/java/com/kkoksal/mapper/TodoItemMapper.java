package com.kkoksal.mapper;

import com.kkoksal.dto.request.TodoItemRequest;
import com.kkoksal.model.TodoItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TodoItemMapper {

    TodoItem convertItemRequestToTodoItem(TodoItemRequest itemRequest);

}
