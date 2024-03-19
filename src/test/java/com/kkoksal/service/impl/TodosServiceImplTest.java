package com.kkoksal.service.impl;

import com.kkoksal.dto.request.TodoItemRequest;
import com.kkoksal.dto.response.TodoResponse;
import com.kkoksal.dto.response.TodoResponsePageable;
import com.kkoksal.exception.TodoItemNotFoundException;
import com.kkoksal.mapper.TodoItemMapper;
import com.kkoksal.model.TodoItem;
import com.kkoksal.repository.TodoRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TodosServiceImplTest {

    @InjectMocks
    public TodosServiceImpl todosService;

    @Mock
    private TodoRepository todoRepository;

    @Mock
    private TodoItemMapper todoItemMapper;

    private TodoItemRequest todoItemRequest;

    private TodoItem todoItem;

    private TodoResponse todoResponse;

    private String userId;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        userId =  UUID.randomUUID().toString();
        todoItemRequest = new TodoItemRequest();
        todoItemRequest.setTitle("Example title");
        todoItemRequest.setContent("Example description");

        todoItem = new TodoItem();
        todoItem.setTitle(todoItemRequest.getTitle());
        todoItem.setContent(todoItemRequest.getContent());
        todoItem.setUserId(userId);

        todoResponse = new TodoResponse();
        todoResponse.setTitle(todoItemRequest.getTitle());
        todoResponse.setContent(todoItemRequest.getContent());
        todoResponse.setCompleted(false);
    }


    @Test
    public void addTodoItemSuccessCase001() {
        when(todoItemMapper.convertItemRequestToTodoItem(any())).thenReturn(todoItem);
        when(todoRepository.save(any())).thenReturn(todoItem);
        when(todoItemMapper.convertTodoItemToTodoResponse(any())).thenReturn(todoResponse);

        TodoResponse actualResponse = todosService.addTodoItem(userId, todoItemRequest);

        verify(todoItemMapper).convertItemRequestToTodoItem(todoItemRequest);
        verify(todoRepository).save(any());
        verify(todoItemMapper).convertTodoItemToTodoResponse(todoItem);

        assertEquals(todoResponse.getTitle(), actualResponse.getTitle());
        assertEquals(todoResponse.getContent(), actualResponse.getContent());
        assertEquals(todoResponse.getCompleted(), false);
    }

    @Test(expected = TodoItemNotFoundException.class)
    public void editTodoItemFailCase001() {
        when(todoRepository.findByUserIdAndId(anyString(), anyString())).thenReturn(Optional.empty());
        todosService.editTodoItem(anyString(), anyString(), todoItemRequest);
    }

    @Test()
    public void editTodoItemSuccessCase002() {
        when(todoRepository.findByUserIdAndId(anyString(), anyString())).thenReturn(Optional.of(todoItem));
        when(todoRepository.save(any())).thenReturn(todoItem);
        when(todoItemMapper.convertTodoItemToTodoResponse(any())).thenReturn(todoResponse);

        TodoResponse actualResponse = todosService.editTodoItem(anyString(), anyString(), todoItemRequest);

        verify(todoRepository, times(1)).findByUserIdAndId(anyString(), anyString());
        verify(todoItemMapper, times(1)).convertTodoItemToTodoResponse(any());
        verify(todoRepository, times(1)).save(any());

        assertEquals(todoResponse.getTitle(), actualResponse.getTitle());
        assertEquals(todoResponse.getContent(), actualResponse.getContent());
        assertEquals(todoResponse.getCompleted(), actualResponse.getCompleted());
    }

    @Test
    public void getAllTodoItemsSuccessCase001() {
        List<TodoItem> todoItemList = new ArrayList<>();
        List<TodoResponse> todoItemResp = new ArrayList<>();
        todoItemList.add(todoItem);
        todoItemList.add(todoItem);
        PageRequest pageable = PageRequest.of(0, 5);
        PageImpl<TodoItem> pageResp = new PageImpl<>(todoItemList, pageable, todoItemList.size());
        for (int i = 0; i <todoItemList.size(); i++) {
            todoItemResp.add(new TodoResponse());
        }
        when(todoItemMapper.convertTodoItemListToTodoResponse(any())).thenReturn(todoItemResp);
        when(todoRepository.findAllByUserId(any(), anyString())).thenReturn(pageResp);

        TodoResponsePageable allTodoItems = todosService.getAllTodoItems(anyString(), any());
        assertEquals(allTodoItems.getTodoItems().size(), 2);
    }

    @Test(expected = TodoItemNotFoundException.class)
    public void getTodoItemByIdFailCase001() {
        when(todoRepository.findByUserIdAndId(anyString(), anyString())).thenReturn(Optional.empty());
        todosService.getTodoItemById(anyString(), anyString());
    }

    @Test
    public void getTodoItemByIdSuccessCase002() {
        when(todoRepository.findByUserIdAndId(anyString(), anyString())).thenReturn(Optional.of(todoItem));
        todosService.getTodoItemById(anyString(), anyString());
        verify(todoRepository, times(1)).findByUserIdAndId(anyString(), anyString());
        verify(todoItemMapper, times(1)).convertTodoItemToTodoResponse(any());
    }

    @Test
    public void deleteTodoItemSuccessCase001() {
        doNothing().when(todoRepository).deleteByUserIdAndId(anyString(), anyString());
        todosService.deleteTodoItem(anyString(), anyString());
        verify(todoRepository, times(1)).deleteByUserIdAndId(anyString(), anyString());
    }

    @Test(expected = TodoItemNotFoundException.class)
    public void completeTodoItemFailCase001() {
        when(todoRepository.findByUserIdAndId(anyString(), anyString())).thenReturn(Optional.empty());
        todosService.completeTodoItem(anyString(), anyString());
    }

    @Test
    public void completeTodoItemSuccessCase002() {
        when(todoRepository.findByUserIdAndId(anyString(), anyString())).thenReturn(Optional.of(todoItem));
        when(todoItemMapper.convertTodoItemToTodoResponse(any())).thenReturn(todoResponse);
        when(todoRepository.save(any())).thenReturn(todoItem);

        todosService.completeTodoItem(anyString(), anyString());

        verify(todoRepository, times(1)).findByUserIdAndId(anyString(), anyString());
        verify(todoItemMapper, times(1)).convertTodoItemToTodoResponse(any());
        verify(todoRepository, times(1)).save(any());
    }
}