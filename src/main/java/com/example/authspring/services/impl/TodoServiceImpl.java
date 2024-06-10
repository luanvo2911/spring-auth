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
  public List<Todo> getAllTodos(Integer item_per_page, Integer page){
    return todoRepository.getAllTodos(item_per_page, page);
  }

  @Override
  public List<Todo> getTodosByUserId(Integer Id, Integer item_per_page, Integer page){
    return todoRepository.getTodosByUserId(Id, item_per_page, page);
  }

  @Override
  public void insertNewTodos(Integer user_id, String todos){
    todoRepository.createNewTodos(user_id, todos);
  }

  @Override
  public void updateTodos(Todo todos){
    todoRepository.updateTodos(todos.id, todos.todos);
  }

  @Override
  public void deleteTodos(Integer id){
    todoRepository.deleteTodos(id);
  }
}
