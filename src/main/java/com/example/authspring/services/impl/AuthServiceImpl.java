package com.example.authspring.services.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.authspring.dto.JwtAuthenticationResponse;
import com.example.authspring.dto.RefreshTokenRequest;
import com.example.authspring.dto.SignInRequest;
import com.example.authspring.dto.SignUpRequest;
import com.example.authspring.repository.UserRepository;
import com.example.authspring.services.AuthService;
import com.example.authspring.services.JWTService;
// import com.example.authspring.entities.Role;
import com.example.authspring.entities.User;

import java.util.HashMap;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
  
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final JWTService jwtService;

  public User signup(SignUpRequest signUpRequest){
    User user = new User();
    
    user.setEmail(signUpRequest.getEmail());
    user.setFirstname(signUpRequest.getFirstname());
    user.setSecondname(signUpRequest.getSecondname());
    user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
    user.setRole(signUpRequest.getRole());
    System.out.println(user);

    return userRepository.save(user);
  }

  public JwtAuthenticationResponse signin(SignInRequest signInRequest){
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));
    var user = userRepository.findByEmail(signInRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid email and password"));
    var jwt = jwtService.generateToken((user));
    var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

    JwtAuthenticationResponse jwtResponse = new JwtAuthenticationResponse();
    jwtResponse.setToken(jwt);
    jwtResponse.setRefreshToken(refreshToken);

    return jwtResponse;

  }

  public JwtAuthenticationResponse refreshToken(RefreshTokenRequest requestTokenRequest){
    String userEmail = jwtService.extractUsername(requestTokenRequest.getToken());
    User user = userRepository.findByEmail(userEmail).orElseThrow();
    if(jwtService.isTokenValid(requestTokenRequest.getToken(), user)){
      var jwt = jwtService.generateToken(user);
      JwtAuthenticationResponse jwtResponse = new JwtAuthenticationResponse();
      jwtResponse.setToken(jwt);
      jwtResponse.setRefreshToken(requestTokenRequest.getToken());
      return jwtResponse;
    }
    return null;
  }

}
