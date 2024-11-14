package com.anhembiblog.anhembiblog.service;

import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anhembiblog.anhembiblog.model.User;
import com.anhembiblog.anhembiblog.repository.UserRepo;

@Service
public class UserService {
    
    @Autowired
    private UserRepo repo;

    public User findById(Long id) {
        Optional<User> user = this.repo.findById(id);
        return user.orElseThrow(() -> new RuntimeException(
            "Usuário não encontrado" + id + ", tipo:" + User.class.getName()
        ));
    }

    @Transactional
    public User create(User obj) {
        obj.setId(null);
        obj = this.repo.save(obj);
        return obj;
    }

    @Transactional
    public User update(User obj) {
        User newObj = findById(obj.getId());
        newObj.setPassword(obj.getPassword());
        return this.repo.save(newObj);
    }


    public void delete (Long id) {
        findById(id);
        try {
            this.repo.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir pois há entidades relacionadas!");        
        }
    }
}
