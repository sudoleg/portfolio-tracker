package com.sudoleg.portfoliomanager.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sudoleg.portfoliomanager.TestDataUtil;
import com.sudoleg.portfoliomanager.domain.dto.UserDto;
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

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

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
        public void testUserCreationReturns201Created() throws Exception {
                UserEntity userEntity = TestDataUtil.createTestUserA();
                userEntity.setId(null);
                String userJson = objectMapper.writeValueAsString(userEntity);

                mockMvc.perform(
                                MockMvcRequestBuilders.post("/users")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(userJson))
                                .andExpect(
                                                MockMvcResultMatchers.status().isCreated());
        }

        @Test
        public void testUserCreationReturnsSavedUSer() throws Exception {
                UserEntity testUserA = TestDataUtil.createTestUserA();
                testUserA.setId(null);
                String userJson = objectMapper.writeValueAsString(testUserA);

                mockMvc.perform(
                                MockMvcRequestBuilders.post("/users")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(userJson))
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$.id").isNumber())
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$.name").value(testUserA.getName()))
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$.surname")
                                                                .value(testUserA.getSurname()));
        }

        @Test
        public void testListAllUsersReturnsOk() throws Exception {
                mockMvc.perform(
                                MockMvcRequestBuilders.get("/users")).andExpect(
                                                MockMvcResultMatchers.status().is2xxSuccessful());
        }

        @Test
        public void testListAllUsersReturnsCorrectList() throws Exception {
                UserEntity userA = TestDataUtil.createTestUserA();
                userService.save(userA);

                mockMvc.perform(
                                MockMvcRequestBuilders.get("/users")).andExpect(
                                                MockMvcResultMatchers.jsonPath("[0].id").isNumber())
                                .andExpect(
                                                MockMvcResultMatchers
                                                                .jsonPath("[0].username").value(userA.getUsername()))
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("[0].name").value(userA.getName()));
        }

        @Test
        public void testGetUserByIdReturns404IfNotExist() throws Exception {
                mockMvc.perform(
                                MockMvcRequestBuilders.get("/users/42")).andExpect(
                                                MockMvcResultMatchers.status().isNotFound());
        }

        @Test
        public void testGetUserByIdReturnsCorrectUser() throws Exception {
                UserEntity userA = TestDataUtil.createTestUserA();
                userService.save(userA);

                mockMvc.perform(
                                MockMvcRequestBuilders.get("/users/1")).andExpect(
                                                MockMvcResultMatchers.jsonPath("$.id").isNumber())
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$.username").value(userA.getUsername()))
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$.name").value(userA.getName()));
        }

        @Test
        public void testUserFullUpdateReturnsUpdatedUser() throws Exception {
                UserEntity userEntity = TestDataUtil.createTestUserA();
                UserEntity savedUser = userService.save(userEntity);

                UserDto userDto = TestDataUtil.createTestUserDtoA();
                userDto.setUsername("UPDATED");

                String userJson = objectMapper.writeValueAsString(userDto);

                mockMvc.perform(
                                MockMvcRequestBuilders.put("/users/" + savedUser.getId())
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(userJson))
                                .andExpect(
                                                MockMvcResultMatchers.status().isOk())
                                .andExpect(
                                                MockMvcResultMatchers
                                                                .jsonPath("$.username").value(userDto.getUsername()))
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$.id").value(userDto.getId()))
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$.email").value(userDto.getEmail()));
        }

        @Test
        public void testPartialUpdateOnExistingUserReturnsHttpOk() throws Exception {
                UserEntity userEntity = TestDataUtil.createTestUserA();
                UserEntity savedUser = userService.save(userEntity);

                UserDto testUserDto = TestDataUtil.createTestUserDtoA();
                testUserDto.setName("UPDATED");
                String userDtoJson = objectMapper.writeValueAsString(testUserDto);

                mockMvc.perform(
                                MockMvcRequestBuilders.patch("/users/" + savedUser.getId())
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(userDtoJson))
                                .andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        public void testPartialUpdateOnExistingUserReturnsUpdatedUser() throws Exception {
                UserEntity userEntity = TestDataUtil.createTestUserA();
                UserEntity savedUser = userService.save(userEntity);

                UserDto testUserDto = TestDataUtil.createTestUserDtoA();
                testUserDto.setName("UPDATED");
                String userDtoJson = objectMapper.writeValueAsString(testUserDto);

                mockMvc.perform(
                                MockMvcRequestBuilders.patch("/users/" + savedUser.getId())
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(userDtoJson))
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$.name").value("UPDATED"))
                                .andExpect(
                                                MockMvcResultMatchers
                                                                .jsonPath("$.surname").value(savedUser.getSurname()))
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$.id").value(savedUser.getId()))
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$.email").value(savedUser.getEmail()));
        }

        @Test
        public void testDeleteUserReturns204NoContent() throws Exception {
                UserEntity userEntity = TestDataUtil.createTestUserA();
                UserEntity savedUser = userService.save(userEntity);

                mockMvc.perform(
                                MockMvcRequestBuilders.delete("/users/" + savedUser.getId())).andExpect(
                                                MockMvcResultMatchers.status().isNoContent());

                Optional<UserEntity> result = userService.findOne(userEntity.getId());
                assertThat(result).isEmpty();
        }

        @Test
        public void testDeleteNonExistentUserReturns404() throws Exception {
                mockMvc.perform(
                                MockMvcRequestBuilders.delete("/users/999")).andExpect(
                                                MockMvcResultMatchers.status().isNotFound());
        }

}
