package com.kkoksal.service;

import com.kkoksal.dto.request.TodoItemRequest;
import com.kkoksal.model.TodoItem;

import java.util.List;

public interface TodosService {
    void addTodoItem(TodoItemRequest todoItem);

    void editTodoItem(String itemId, TodoItemRequest todoItem);

    List<TodoItem> getAllTodoItems(String userId);

    TodoItem getTodoItemById(String itemId, String userId);

    void deleteTodoItem(String itemId, String userId);
}
