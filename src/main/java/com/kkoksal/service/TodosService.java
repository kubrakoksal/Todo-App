package com.kkoksal.service;

import com.kkoksal.dto.request.TodoItemRequest;
import com.kkoksal.dto.response.TodoResponse;
import com.kkoksal.dto.response.TodoResponsePageable;
import org.springframework.data.domain.Pageable;

public interface TodosService {
    TodoResponse addTodoItem(String userId, TodoItemRequest todoItem);

    TodoResponse editTodoItem(String userId, String itemId, TodoItemRequest todoItem);

    TodoResponsePageable getAllTodoItems(String userId, Pageable pageable);

    TodoResponse getTodoItemById(String itemId, String userId);

    void deleteTodoItem(String itemId, String userId);

    TodoResponse completeTodoItem(String itemId, String userId);
}
