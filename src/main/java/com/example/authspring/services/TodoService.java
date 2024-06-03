package com.example.authspring.services;

import com.example.authspring.entities.Todo;

import java.util.List;

public interface TodoService {
  public List<Todo> getAllTodos();

  public List<Todo> getTodosByUserId(Integer Id);
}
