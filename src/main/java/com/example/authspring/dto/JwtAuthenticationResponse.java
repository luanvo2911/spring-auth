package com.example.authspring.dto;


import com.example.authspring.entities.Role;
import lombok.Data;

@Data
public class JwtAuthenticationResponse {
  private String token;

  private String refreshToken;

  private Role role;
}
