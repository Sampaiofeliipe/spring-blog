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

import com.anhembiblog.anhembiblog.model.User;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import com.anhembiblog.anhembiblog.service.UserService;

@WebMvcTest(UserController.class)
public class UseControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testFindById() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setUsername("UsernameTest");

        when(userService.findById(1L)).thenReturn(user);

        mockMvc.perform(get("/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("UsernameTest"));

        verify(userService, times(1)).findById(1L);
    }

    @Test
    void testCreate() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setUsername("UsernameTest");
        user.setPassword("userpassword");
        when(userService.create(any(User.class))).thenReturn(user);
        String userJson = """
                {
                    "username": "UsernameTest",
                    "password": "userpassword"
                }
                """;

        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));

        verify(userService, times(1)).create(any(User.class));
    }

    @Test
    void testUpdate() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setEmail("usernametest@gmail.com");
        user.setPassword("usernamepassword");

        when(userService.update(any(User.class))).thenReturn(user);

        String userJson = """
                {
                    "email": "usernametest@gmail.com",
                    "password": "usernamepassword"
                }
                """;

        mockMvc.perform(put("/user/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isNoContent());

        verify(userService, times(1)).update(any(User.class));
    }

    @Test
    void testDelete() throws Exception {
        doNothing().when(userService).delete(1L);

        mockMvc.perform(delete("/user/1"))
                .andExpect(status().isNoContent());

        verify(userService, times(1)).delete(1L);
    }

}
