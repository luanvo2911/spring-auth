package com.example.authspring.dto;

import com.example.authspring.entities.Role;

import lombok.Data;

@Data
public class SignUpRequest {
  
  private String firstname;
  private String secondname;
  private String email;
  private String password;
  private Role role;

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public void setSecondname(String secondname) {
    this.secondname = secondname;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setRole(Role role){
    this.role = role;
  }

  public String getEmail() {
    return this.email;
  }

  public String getFirstname() {
    return this.firstname;
  }

  public String getSecondname() {
    return this.secondname;
  }

  public String getPassword() {
    return this.password;
  }

  public Role getRole(){
    return this.role;
  }

}
