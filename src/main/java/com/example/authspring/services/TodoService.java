package com.example.authspring.services;

import com.example.authspring.entities.Todo;

import java.util.List;

public interface TodoService {
  public List<Todo> getAllTodos();

  public List<Todo> getTodosByUserId(Integer Id);

  public void insertNewTodos(Integer user_id, String todos);

  public void updateTodos(Todo todos);

  public void deleteTodos(Todo todos);
}
