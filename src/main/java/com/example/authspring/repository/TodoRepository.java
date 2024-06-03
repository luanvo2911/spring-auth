package com.example.authspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.authspring.entities.Todo;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
  @Query(value = "SELECT todos.id, user_id, todos FROM todos, users WHERE todos.user_id = users.id", nativeQuery = true)
    List<Todo> getAllTodos();

  @Query(value = "SELECT todos.id, user_id, todos FROM todos, users WHERE todos.user_id = users.id and users.id = :id", nativeQuery = true)
    List<Todo> getTodosByUserId(@Param("id") Integer id);  
}
