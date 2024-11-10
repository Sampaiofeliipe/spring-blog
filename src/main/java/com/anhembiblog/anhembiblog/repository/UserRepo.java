package com.anhembiblog.anhembiblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.anhembiblog.anhembiblog.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
