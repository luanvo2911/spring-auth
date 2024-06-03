package com.example.authspring.services;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JWTService {
  String generateToken(UserDetails userDetails);

  String generateRefreshToken(Map<String, Object> extraClaims  ,UserDetails userDetails);

  String extractUsername(String token);

  Boolean isTokenValid(String token, UserDetails userDetails);

}
