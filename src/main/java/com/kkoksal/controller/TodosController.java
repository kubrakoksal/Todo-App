package com.kkoksal.controller;

import com.kkoksal.config.LoggedInUser;
import com.kkoksal.config.security.UserPrincipal;
import com.kkoksal.dto.request.TodoItemRequest;
import com.kkoksal.dto.response.TodoResponse;
import com.kkoksal.dto.response.TodoResponsePageable;
import com.kkoksal.service.TodosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/todo")
@RequiredArgsConstructor
@Tag(name = "Todo", description = "Todo CRUD Operations Service")
@SecurityRequirement(name = "Bearer Authentication")
public class TodosController {

    private final TodosService todosService;

    @PostMapping
    @Operation(summary = "Add todo item")
    public ResponseEntity<TodoResponse> addTodoItem(@LoggedInUser UserPrincipal userPrincipal, @RequestBody @Valid TodoItemRequest todoItemRequest) {
        return ResponseEntity.ok(todosService.addTodoItem(userPrincipal.getId(), todoItemRequest));

    }

    @PutMapping("/{itemId}")
    @Operation(summary = "Edit todo item")
    public ResponseEntity<TodoResponse> editTodoItem(@LoggedInUser UserPrincipal userPrincipal, @PathVariable String itemId, @RequestBody @Valid TodoItemRequest todoItemRequest) {
        todosService.editTodoItem(userPrincipal.getId(), itemId, todoItemRequest);
        return ResponseEntity.ok(todosService.editTodoItem(userPrincipal.getId(), itemId, todoItemRequest));

    }

    @GetMapping("/items")
    @Operation(summary = "Get all todo items of logged in user")
    public ResponseEntity<TodoResponsePageable> getAllTodoItems(@LoggedInUser UserPrincipal userPrincipal, Pageable pageable) {
        return ResponseEntity.ok(todosService.getAllTodoItems(userPrincipal.getId(), pageable));
    }

    @GetMapping("/item/{itemId}")
    @Operation(summary = "Get todo item by id")
    public ResponseEntity<TodoResponse> getTodoItemById(@PathVariable String itemId, @LoggedInUser UserPrincipal userPrincipal) {
        return ResponseEntity.ok(todosService.getTodoItemById(itemId, userPrincipal.getId()));
    }

    @DeleteMapping("/item/{itemId}")
    @Operation(summary = "Delete todo item by id")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTodoItem(@PathVariable String itemId, @LoggedInUser UserPrincipal userPrincipal) {
        todosService.deleteTodoItem(itemId, userPrincipal.getId());
    }

    @PutMapping("/item/{itemId}/complete")
    @Operation(summary = "Complete todo item by id")
    public ResponseEntity<TodoResponse> completeTodoItem(@PathVariable String itemId, @LoggedInUser UserPrincipal userPrincipal) {
        return ResponseEntity.ok(todosService.completeTodoItem(itemId, userPrincipal.getId()));
    }

}
