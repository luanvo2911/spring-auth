package com.example.authspring.services.impl;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import com.example.authspring.repository.UserRepository;
import com.example.authspring.services.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.RequiredArgsConstructor;
// import lombok.NonNull;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public UserDetailsService userDetailsService() {
    return new UserDetailsService() {
      @Override
      public UserDetails loadUserByUsername(String username) {
        return userRepository.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
      }
    };
  }

}
