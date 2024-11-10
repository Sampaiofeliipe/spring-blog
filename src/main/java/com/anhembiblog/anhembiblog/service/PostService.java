package com.anhembiblog.anhembiblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anhembiblog.anhembiblog.model.Post;
import com.anhembiblog.anhembiblog.repository.PostRepo;

@Service
public class PostService {
    
    @Autowired
    private PostRepo repo;

    public List<Post> getAllPosts() {
        return repo.findAll();
    }

    public Post getPostById(int id) {
        return repo.findById(id).orElse(null);
    }

    public List<Post> getPostsByUser(int userID) {
        return repo.findByAuthorId(userID);
    }

    public Post save(Post post) {
        return repo.save(post);
    }
}
