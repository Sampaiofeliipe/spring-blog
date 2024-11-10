package com.anhembiblog.anhembiblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anhembiblog.anhembiblog.model.Post;
import java.util.List;


public interface PostRepo extends JpaRepository<Post, Integer>{
    List<Post> findByAuthorId(int userId);
}
