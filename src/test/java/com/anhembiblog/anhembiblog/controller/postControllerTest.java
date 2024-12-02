package com.anhembiblog.anhembiblog.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.anhembiblog.anhembiblog.model.Post;
import com.anhembiblog.anhembiblog.model.User;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.anhembiblog.anhembiblog.service.PostService;
import com.anhembiblog.anhembiblog.service.UserService;

@WebMvcTest(PostController.class)
public class postControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @Test
    public void testFindById() throws Exception {
        Post post = new Post();
        post.setTitle("Post Title");
        post.setContent("Post conteudo");

        when(postService.findById(1L)).thenReturn(post);

        mockMvc.perform(get("/post/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Post Title"))
                .andExpect(jsonPath("$.content").value("Post conteudo"));

        verify(postService, times(1)).findById(1L);
    }

    @Test
    void testCreate() throws Exception {
        Post post = new Post();
        post.setId(1L);
        post.setTitle("Post Title");
        post.setContent("Conteudo do Post");
        when(postService.create(any(Post.class))).thenReturn(post);
        String postJson = """
                {
                    "title": "Post Title",
                    "content": "Conteudo do Post"
                }
                """;

        mockMvc.perform(post("/post")
                .contentType(MediaType.APPLICATION_JSON)
                .content(postJson))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));

        verify(postService, times(1)).create(any(Post.class));
    }

    @Test
    void testUpdate() throws Exception {
        Post post = new Post();
        post.setId(1L);
        post.setTitle("Post title atualizado");
        post.setContent("Post conteudo ");

        when(postService.update(any(Post.class))).thenReturn(post);

        String postJson = """
                {
                    "title": "Post title atualizado",
                    "content": "Post conteudo"
                }
                """;

        mockMvc.perform(put("/post/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(postJson))
                .andExpect(status().isNoContent());

        verify(postService, times(1)).update(any(Post.class));
    }

    @Test
    void testDelete() throws Exception {
        doNothing().when(postService).delete(1L);

        mockMvc.perform(delete("/post/1"))
                .andExpect(status().isNoContent());

        verify(postService, times(1)).delete(1L);
    }

}