package com.example.authspring.services;

import com.example.authspring.entities.User;
import com.example.authspring.dto.SignUpRequest;
import com.example.authspring.dto.JwtAuthenticationResponse;
import com.example.authspring.dto.SignInRequest;
import com.example.authspring.dto.RefreshTokenRequest;

public interface AuthService {

  User signup(SignUpRequest signUpRequest);

  JwtAuthenticationResponse signin(SignInRequest signInRequest);

  JwtAuthenticationResponse refreshToken(RefreshTokenRequest requestTokenRequest);
}
