package com.example.authspring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.authspring.services.TodoService;
import com.example.authspring.entities.Todo;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

import lombok.RequiredArgsConstructor;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

  private final TodoService todoService;

  @GetMapping(value = "/{page}/{item_per_page}")
  public ResponseEntity<List<Todo>> GetController(@PathVariable Integer page, @PathVariable Integer item_per_page) {
    return ResponseEntity.ok(todoService.getAllTodos(item_per_page, page));
  }

  @PostMapping
  public ResponseEntity<String> PostController(@RequestBody Todo todos) {
    // Can post todo for every user

    todoService.insertNewTodos(todos.user_id, todos.todos);
    return ResponseEntity.ok("Create Successfully");
  }

  @PutMapping
  public ResponseEntity<String> PutController(@RequestBody Todo todos) {
    todoService.updateTodos(todos);
    return ResponseEntity.ok("Updated Successfully");
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<String> DeleteController(@PathVariable Integer id) {
    todoService.deleteTodos(id);
    return ResponseEntity.ok("Deleted Successfully");
  }
}
