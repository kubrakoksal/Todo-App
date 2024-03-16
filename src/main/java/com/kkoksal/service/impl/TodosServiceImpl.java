package com.kkoksal.service.impl;

import com.kkoksal.dto.request.TodoItemRequest;
import com.kkoksal.mapper.TodoItemMapper;
import com.kkoksal.model.TodoItem;
import com.kkoksal.repository.TodoRepository;
import com.kkoksal.service.TodosService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodosServiceImpl implements TodosService {

    private final TodoItemMapper todoItemMapper;

    private final TodoRepository todoRepository;

    @Override
    public void addTodoItem(TodoItemRequest todoItemRequest) {
        TodoItem todoItem = todoItemMapper.convertItemRequestToTodoItem(todoItemRequest);
        todoRepository.save(todoItem);
    }

    @Override
    public void editTodoItem(String itemId, TodoItemRequest todoItemRequest) {
        Optional<TodoItem> todoItemOptional = todoRepository.findById(itemId);
        if (todoItemOptional.isEmpty()) {
            throw new RuntimeException("");
        }
        TodoItem todoItem = todoItemOptional.get();
        todoItem.setContent(todoItemRequest.getContent());
        todoItem.setTitle(todoItemRequest.getTitle());
        todoRepository.save(todoItem);
    }

    @Override
    public List<TodoItem> getAllTodoItems(String userId) {
       return todoRepository.findAllByUserId(userId);
    }

    @Override
    public TodoItem getTodoItemById(String itemId, String userId) {
        Optional<TodoItem> optionalTodoItem = todoRepository.findByUserIdAndId(userId, itemId);
        if (optionalTodoItem.isEmpty()) {
            throw new RuntimeException("");
        }
        return optionalTodoItem.get();
    }

    @Override
    public void deleteTodoItem(String itemId, String userId) {
        todoRepository.deleteByUserIdAndId(userId, itemId);
    }
}
