package com.sudoleg.portfoliomanager.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sudoleg.portfoliomanager.TestDataUtil;
import com.sudoleg.portfoliomanager.domain.entities.UserEntity;
import com.sudoleg.portfoliomanager.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class UserControllerIntegrationTests {

    private MockMvc mockMvc;
    private UserService userService;

    private ObjectMapper objectMapper;

    @Autowired
    public UserControllerIntegrationTests(MockMvc mockMvc, UserService userService, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.userService = userService;
        this.objectMapper = objectMapper;
    }

    @Test
    public void testUserCreationReturnsExpectedStatusCode() throws Exception {
        UserEntity userEntity = TestDataUtil.createTestUserA();
        userEntity.setUserId(null);
        String userJson = objectMapper.writeValueAsString(userEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testUserCreationReturnsSavedUSer() throws Exception {
        UserEntity testUserA = TestDataUtil.createTestUserA();
        testUserA.setUserId(null);
        String userJson = objectMapper.writeValueAsString(testUserA);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.userId").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value(testUserA.getName())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.surname").value(testUserA.getSurname())
        );
    }

    @Test
    public void testListAllUsersReturnsOk() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/users")
        ).andExpect(
                MockMvcResultMatchers.status().is2xxSuccessful()
        );
    }

    @Test
    public void testListAllUsersReturnsCorrectList() throws Exception {
        UserEntity userA = TestDataUtil.createTestUserA();
        userService.createUser(userA);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/users")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("[0].userId").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("[0].username").value(userA.getUsername())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("[0].name").value(userA.getName())
        );
    }

}
