package com.example.authspring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.authspring.dto.JwtAuthenticationResponse;
import com.example.authspring.dto.SignInRequest;
import com.example.authspring.dto.SignUpRequest;
import com.example.authspring.dto.RefreshTokenRequest;
import com.example.authspring.entities.User;
// import com.example.authspring.repository.UserRepository;
import com.example.authspring.services.AuthService;
// import com.example.authspring.entities.Role;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  // private final UserRepository userRepository;

  @PostMapping("/signup")
  public ResponseEntity<User> signup(@RequestBody SignUpRequest signUpRequest){
    System.out.println(signUpRequest);
    return ResponseEntity.ok(authService.signup(signUpRequest));
  }

  @PostMapping("/signin")
  public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest signInRequest){
    return ResponseEntity.ok(authService.signin(signInRequest));
  }

  @PostMapping("/refresh")
  public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
    return ResponseEntity.ok(authService.refreshToken(refreshTokenRequest));
  }
  
}
