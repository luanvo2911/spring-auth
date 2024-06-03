package com.example.authspring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;

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
    if(user.role == Role.ADMIN){
      return ResponseEntity.ok(todoService.getAllTodos());
    }
    else{
      return ResponseEntity.ok(todoService.getTodosByUserId(user.id));
    }
  }
}
