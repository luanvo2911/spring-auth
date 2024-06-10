package com.example.authspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.authspring.entities.Todo;

import jakarta.transaction.Transactional;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
  @Query(value = "SELECT todos.id, user_id, todos FROM todos, users WHERE todos.user_id = users.id ORDER BY todos.id LIMIT :item_per_page OFFSET :page", nativeQuery = true)
  List<Todo> getAllTodos(@Param("item_per_page") Integer item_per_page, @Param("page") Integer page);

  @Query(value = "SELECT todos.id, user_id, todos FROM todos, users WHERE todos.user_id = users.id and users.id = :id ORDER BY todos.id LIMIT :item_per_page OFFSET :page", nativeQuery = true)
  List<Todo> getTodosByUserId(@Param("id") Integer id, @Param("item_per_page") Integer item_per_page, @Param("page") Integer page);

  @Modifying
  @Transactional
  @Query(value = "INSERT INTO todos(user_id, todos) VALUES(:user_id, :todos)", nativeQuery = true)
  void createNewTodos(@Param("user_id") Integer user_id, @Param("todos") String todos);

  @Modifying
  @Transactional
  @Query(value = "UPDATE todos SET todos = :todos WHERE todos.id = :id", nativeQuery = true)
  void updateTodos(@Param("id") Integer id, @Param("todos") String todos);

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM todos WHERE todos.id = :id", nativeQuery = true)
  void deleteTodos(@Param("id") Integer id);
}
