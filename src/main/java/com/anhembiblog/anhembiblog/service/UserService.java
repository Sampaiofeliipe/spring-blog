package com.anhembiblog.anhembiblog.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anhembiblog.anhembiblog.model.User;
import com.anhembiblog.anhembiblog.repository.UserRepo;

@Service
public class UserService {
    
    @Autowired
    private UserRepo repo;

    public User findByUsername(String username) {
        return repo.findByUsername(username);
    }
    
    public User save(User user) {
        return repo.save(user);
    }
}
