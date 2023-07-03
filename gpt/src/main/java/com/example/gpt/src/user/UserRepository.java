package com.example.gpt.src.user;

import com.example.gpt.src.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
