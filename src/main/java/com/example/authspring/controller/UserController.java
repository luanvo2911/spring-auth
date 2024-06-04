package com.example.authspring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.authspring.entities.Role;
import com.example.authspring.entities.User;
import com.example.authspring.entities.Todo;
import com.example.authspring.services.TodoService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

  public final TodoService todoService;

  @GetMapping
  public ResponseEntity<List<Todo>> GetController() {
    // user context is stored in SecurityContextHolder

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User user = (User) authentication.getPrincipal();

    return ResponseEntity.ok(todoService.getTodosByUserId(user.id));
    
  }

  @PostMapping
  public ResponseEntity<String> PostController(@RequestBody Todo todos){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User user = (User) authentication.getPrincipal();

    todoService.insertNewTodos(user.id, todos.todos);


    return ResponseEntity.ok("Created Successfully");
  }

  @PutMapping
  public ResponseEntity<String> PutController(@RequestBody Todo todos){
    todoService.updateTodos(todos);
    return ResponseEntity.ok("Updated Successfully");
  }  

  @DeleteMapping
  public ResponseEntity<String> DeleteController(@RequestBody Todo todos){
    todoService.deleteTodos(todos);
    return ResponseEntity.ok("Deleted Successfully");
  }

}
