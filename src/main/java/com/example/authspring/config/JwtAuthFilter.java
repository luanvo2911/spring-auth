package com.example.authspring.config;

import org.springframework.stereotype.Component;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;
import com.example.authspring.services.JWTService;
import com.example.authspring.services.UserService;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import io.micrometer.common.util.StringUtils;
import io.jsonwebtoken.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

  private final JWTService jwtService;

  private final UserService userService;

  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain)
      throws ServletException, IOException {
    final String authHeader = request.getHeader("Authorization");
    final String jwt;
    final String userEmail;

    if (StringUtils.isEmpty(authHeader) || !org.apache.commons.lang3.StringUtils.startsWith(authHeader, "Bearer ")) {
      try {
        filterChain.doFilter(request, response);
      } catch (java.io.IOException e) {
        System.out.println(e);
      }
      return;
    }
    jwt = authHeader.substring(7);
    System.out.println("jwt: " + jwt);
    userEmail = jwtService.extractUsername(jwt);
    System.out.println("userEmail: " + userEmail);

    if (StringUtils.isEmpty(userEmail) || SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = userService.userDetailsService().loadUserByUsername(userEmail);
      if (jwtService.isTokenValid(jwt, userDetails)) {
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, null,
            userDetails.getAuthorities());
        token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        securityContext.setAuthentication(token);
        SecurityContextHolder.setContext(securityContext);
      }
    }
    try {
      filterChain.doFilter(request, response);
    } catch (java.io.IOException e) {
      System.out.println(e);
    }
  }

}
