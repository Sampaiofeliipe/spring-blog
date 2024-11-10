package com.anhembiblog.anhembiblog.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anhembiblog.anhembiblog.model.Post;
import com.anhembiblog.anhembiblog.model.User;
import com.anhembiblog.anhembiblog.service.PostService;
import com.anhembiblog.anhembiblog.service.UserService;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class BlogController {
    
    @Autowired
    private UserService userservice;

    @Autowired
    private PostService postservice;

    @GetMapping("/")
    public String home (Model model) {
        model.addAttribute("posts", postservice.getAllPosts());
        return "home";
    }

    @GetMapping("/post/{id}")
    public String postDetail(@PathVariable int id, Model model) {
        model.addAttribute("post", postservice.getPostById(id));
        return "post-detail";
    }

    @GetMapping("/user/{username}")
    public String userPosts(@PathVariable String username, Model model) {
        User user = userservice.findByUsername(username);
        if (user != null) {
            model.addAttribute("posts", postservice.getPostsByUser(user.getId()));
            model.addAttribute("user", user);
        }
    
        return "user-posts";
    }

    @PostMapping("/createPost")
    public String createPost(@RequestParam  String title, @RequestParam String content, @RequestParam int userID) {
        
        User user = userservice.findByUsername("defaultUser"); // Exemplo, pode ser feito via login
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setAuthor(user);
        postservice.save(post);
        return "redirect:/";
    }
    
    
    



    
}
