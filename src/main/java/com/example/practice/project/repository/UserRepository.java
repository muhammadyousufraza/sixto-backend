package com.example.practice.project.repository;

import com.example.practice.project.entity.User;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CustomRepository<User, Long>{

   Optional<User> findByEmail(String email);
}
