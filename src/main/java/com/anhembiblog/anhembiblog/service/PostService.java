package com.anhembiblog.anhembiblog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anhembiblog.anhembiblog.model.Post;
import com.anhembiblog.anhembiblog.repository.PostRepo;

@Service
public class PostService {

    @Autowired
    private PostRepo repo;

    public List<Post> findAll() {
        return repo.findAll();
    }

    public Post findById(Long id) {
        Optional<Post> post = this.repo.findById(id);
        return post.orElseThrow(() -> new RuntimeException(
                "Post não encontrado"));
    }

    @Transactional
    public Post create(Post obj) {
        obj.setId(null);
        obj = this.repo.save(obj);
        return obj;
    }

    @Transactional
    public Post update(Post obj) {
        Post newObj = findById(obj.getId());
        return this.repo.save(newObj);
    }

    public void delete(Long id) {
        findById(id);
        this.repo.deleteById(id);
    }
}
