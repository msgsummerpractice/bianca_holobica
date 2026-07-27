package com.example.demo.controller;

import com.example.demo.AppSettings;
import com.example.demo.model.User;
import com.example.demo.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc; 

    @MockitoBean
    private IUserService userService; 

    @MockitoBean
    private AppSettings appSettings;

    @Test
    void getUsers_returnsUserList_whenUsersReturnedByService() throws Exception {
        List<User> mockUsers = createMockUsers();
        when(userService.getAllUsers()).thenReturn(mockUsers);

        mockMvc.perform(get("/users").param("minId", "1"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.length()").value(2))          
                .andExpect(jsonPath("$[0].id").value(1))            
                .andExpect(jsonPath("$[0].name").value("John Doe"))   
                .andExpect(jsonPath("$[1].id").value(2))           
                .andExpect(jsonPath("$[1].name").value("Jane Smith"));
    }

    @Test
    void getUsers_returnsBadRequest_whenMinIdIsInvalid() throws Exception {
        mockMvc.perform(get("/users").param("minId", "0"))
            .andExpect(status().isBadRequest());
    }

    private List<User> createMockUsers() {
        return List.of(
            new User(1, "John Doe"),
            new User(2, "Jane Smith")
        );
    }
}