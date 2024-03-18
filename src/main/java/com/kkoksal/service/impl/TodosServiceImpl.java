package com.kkoksal.service.impl;

import com.kkoksal.dto.request.TodoItemRequest;
import com.kkoksal.dto.response.TodoResponse;
import com.kkoksal.dto.response.TodoResponsePageable;
import com.kkoksal.exception.TodoItemNotFoundException;
import com.kkoksal.mapper.TodoItemMapper;
import com.kkoksal.model.TodoItem;
import com.kkoksal.repository.TodoRepository;
import com.kkoksal.service.TodosService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodosServiceImpl implements TodosService {

    private final TodoItemMapper todoItemMapper;

    private final TodoRepository todoRepository;

    @Override
    public void addTodoItem(String userId, TodoItemRequest todoItemRequest) {
        TodoItem todoItem = todoItemMapper.convertItemRequestToTodoItem(todoItemRequest);
        todoItem.setUserId(userId);
        todoItem.setCompleted(false);
        todoRepository.save(todoItem);
    }

    @Override
    public void editTodoItem(String userId, String itemId, TodoItemRequest todoItemRequest) {
        TodoItem todoItem = findTodoItemById(userId, itemId);
        todoItem.setContent(todoItemRequest.getContent());
        todoItem.setTitle(todoItemRequest.getTitle());
        todoRepository.save(todoItem);
    }

    @Override
    public TodoResponsePageable getAllTodoItems(String userId, Pageable pageable) {
        Page<TodoItem> todoItems = todoRepository.findAllByUserId(pageable, userId);
        return TodoResponsePageable.builder().
                todoItems(todoItemMapper.convertTodoItemListToTodoResponse(todoItems.getContent())).
                total(todoItems.getTotalElements()).build();
    }

    @Override
    public TodoResponse getTodoItemById(String itemId, String userId) {
        TodoItem todoItem = findTodoItemById(userId, itemId);
        return todoItemMapper.convertTodoItemToTodoResponse(todoItem);
    }

    @Override
    public void deleteTodoItem(String itemId, String userId) {
        todoRepository.deleteByUserIdAndId(userId, itemId);
    }

    @Override
    public void completeTodoItem(String itemId, String userId) {
        TodoItem todoItem = findTodoItemById(userId, itemId);
        todoItem.setCompleted(true);
        todoRepository.save(todoItem);
    }

    private TodoItem findTodoItemById(String userId, String itemId) {
        Optional<TodoItem> optionalTodoItem = todoRepository.findByUserIdAndId(userId, itemId);
        if (optionalTodoItem.isEmpty()) {
            throw new TodoItemNotFoundException(String.format("Todo item can not be found with id %s", itemId));
        }
        return optionalTodoItem.get();
    }
}
