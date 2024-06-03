package com.example.authspring.services.impl;

import com.example.authspring.entities.Todo;
import com.example.authspring.repository.TodoRepository;
import com.example.authspring.services.TodoService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

  private final TodoRepository todoRepository;

  @Override
  public List<Todo> getAllTodos(){
    return todoRepository.getAllTodos();
  }

  @Override
  public List<Todo> getTodosByUserId(Integer Id){
    return todoRepository.getTodosByUserId(Id);
  }
}
