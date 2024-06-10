package com.example.authspring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

// import com.example.authspring.entities.Role;
import com.example.authspring.entities.User;
import com.example.authspring.entities.Todo;
import com.example.authspring.services.TodoService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

  public final TodoService todoService;

  @GetMapping(value="/{page}/{item_per_page}")
  public ResponseEntity<List<Todo>> GetController(@PathVariable Integer page, @PathVariable Integer item_per_page) {
    // user context is stored in SecurityContextHolder

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User user = (User) authentication.getPrincipal();

    return ResponseEntity.ok(todoService.getTodosByUserId(user.id, item_per_page, page));
    
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

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<String> DeleteController(@PathVariable Integer id){
    todoService.deleteTodos(id);
    return ResponseEntity.ok("Deleted Successfully");
  }

}
