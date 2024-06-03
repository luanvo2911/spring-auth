package com.example.authspring.entities;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

// import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer id;
  // @Column(nullable = false)
  public String firstname;
  // @Column(nullable = false)
  public String secondname;
  // @Column(nullable = false)
  public String email;
  // @Column(nullable = false)
  public String password;
  // @Column(nullable = false)
  public Role role; // USER, ADMIN, GUEST
  // public Status active; // ACTIVE, BANNED

  public void setRole(Role role){
    this.role = role;
  }

  public void setEmail(String email){
    this.email = email;
  }

  public void setFirstname(String firstname){
    this.firstname = firstname;
  }

  public void setSecondname(String secondname){
    this.secondname = secondname;
  }

  public void setPassword(String password){
    this.password = password;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    // return null;
    return List.of(new SimpleGrantedAuthority(role.name()));
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;

  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}
