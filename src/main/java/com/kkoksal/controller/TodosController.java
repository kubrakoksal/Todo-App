package com.kkoksal.controller;

import com.kkoksal.config.LoggedInUser;
import com.kkoksal.config.security.UserPrincipal;
import com.kkoksal.dto.request.TodoItemRequest;
import com.kkoksal.model.TodoItem;
import com.kkoksal.service.TodosService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/todo")
@RequiredArgsConstructor
public class TodosController {

    private final TodosService todosService;

    @PostMapping
    public void addTodoItem(@RequestBody @Valid TodoItemRequest todoItemRequest) {
        todosService.addTodoItem(todoItemRequest);
    }

    @PutMapping("/{itemId}")
    public void editTodoItem(@PathVariable String itemId, @RequestBody @Valid TodoItemRequest todoItemRequest) {
        todosService.editTodoItem(itemId, todoItemRequest);
    }

    @GetMapping("/items/")
    public List<TodoItem> getAllTodoItems(@LoggedInUser UserPrincipal userPrincipal) {
        return todosService.getAllTodoItems(userPrincipal.getId());
    }

    @GetMapping("/item/{itemId}")
    public TodoItem getTodoItemById(@PathVariable String itemId, @LoggedInUser UserPrincipal userPrincipal) {
        return todosService.getTodoItemById(itemId, userPrincipal.getId());
    }

    @DeleteMapping("/item/{itemId}/user/{userId}")
    public void deleteTodoItem(@PathVariable String itemId, @LoggedInUser UserPrincipal userPrincipal) {
        todosService.deleteTodoItem(itemId, userPrincipal.getId());
    }

    @GetMapping("/test/")
    public String test(@LoggedInUser UserPrincipal userPrincipal) throws InterruptedException {
        Thread.sleep(5000);
        return userPrincipal.getUsername();
    }

}
