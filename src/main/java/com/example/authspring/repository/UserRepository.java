package com.example.authspring.repository;

import org.springframework.stereotype.Repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import com.example.authspring.entities.User;
import com.example.authspring.entities.Role;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  // @Override
  Optional<User> findByEmail(@NonNull String email);

  User findByRole(Role role);
}
