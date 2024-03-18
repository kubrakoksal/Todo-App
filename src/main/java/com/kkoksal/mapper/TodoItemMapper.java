package com.kkoksal.mapper;

import com.kkoksal.dto.request.TodoItemRequest;
import com.kkoksal.dto.response.TodoResponse;
import com.kkoksal.model.TodoItem;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TodoItemMapper {


    TodoItem convertItemRequestToTodoItem(TodoItemRequest itemRequest);

    List<TodoResponse> convertTodoItemListToTodoResponse(List<TodoItem> todoItems);


    TodoResponse convertTodoItemToTodoResponse(TodoItem todoItem);

}
