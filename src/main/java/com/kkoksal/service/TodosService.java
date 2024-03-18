package com.kkoksal.service;

import com.kkoksal.dto.request.TodoItemRequest;
import com.kkoksal.dto.response.TodoResponse;
import com.kkoksal.dto.response.TodoResponsePageable;
import org.springframework.data.domain.Pageable;

public interface TodosService {
    void addTodoItem(String userId, TodoItemRequest todoItem);

    void editTodoItem(String userId, String itemId, TodoItemRequest todoItem);

    TodoResponsePageable getAllTodoItems(String userId, Pageable pageable);

    TodoResponse getTodoItemById(String itemId, String userId);

    void deleteTodoItem(String itemId, String userId);

    void completeTodoItem(String itemId, String userId);
}
