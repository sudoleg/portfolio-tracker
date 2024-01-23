package com.sudoleg.portfoliomanager.controllers;

import com.sudoleg.portfoliomanager.domain.dto.UserDto;
import com.sudoleg.portfoliomanager.domain.entities.UserEntity;
import com.sudoleg.portfoliomanager.mappers.Mapper;
import com.sudoleg.portfoliomanager.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Presentation layer.
 * Receives request containing user as JSON in the request body.
 * Maps from user (DTO) to user entity.
 * Performs CRUD on user using service.
 * Optionally: returns answer.
 */
@RestController
public class UserController {

    private UserService userService;

    private Mapper<UserEntity, UserDto> userMapper;

    public UserController(UserService userService, Mapper<UserEntity, UserDto> userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping(path = "/users")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) {
        UserEntity userEntity = userMapper.mapFrom(user);
        UserEntity savedUser = userService.createUser(userEntity);
        return new ResponseEntity<>(userMapper.mapTo(savedUser), HttpStatus.CREATED);
    }

}
