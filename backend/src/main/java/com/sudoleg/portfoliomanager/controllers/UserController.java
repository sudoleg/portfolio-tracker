package com.sudoleg.portfoliomanager.controllers;

import com.sudoleg.portfoliomanager.domain.dto.UserDto;
import com.sudoleg.portfoliomanager.domain.entities.UserEntity;
import com.sudoleg.portfoliomanager.exceptions.UserAlreadyExistsException;
import com.sudoleg.portfoliomanager.mappers.Mapper;
import com.sudoleg.portfoliomanager.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Presentation layer.
 * Handles HTTP requests containing user as JSON in the request body.
 * Maps from JSON user (DTO) to user entity (object).
 * Transfer it to the business layer, which handles the business logic.
 * Optionally: returns answer.
 */
@RestController
public class UserController {

    private final UserService userService;

    private final Mapper<UserEntity, UserDto> userMapper;

    public UserController(UserService userService, Mapper<UserEntity, UserDto> userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping(path = "/users")
    public ResponseEntity createUser(@RequestBody UserDto user) {
        UserEntity userEntity = userMapper.mapFrom(user);
        try {
            UserEntity savedUser = userService.save(userEntity);
            return new ResponseEntity<>(userMapper.mapTo(savedUser), HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity<String>("User with this username or email already exists!", HttpStatus.CONFLICT);
        }
    }

    @GetMapping(path = "/users")
    public List<UserDto> listUsers() {
        List<UserEntity> users = userService.findAll();
        return users.stream().map(userMapper::mapTo).collect(Collectors.toList());
    }

    @GetMapping(path = "/users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Integer id) {
        Optional<UserEntity> result = userService.findOne(id);
        return result.map(userEntity -> {
            UserDto userDto = userMapper.mapTo(userEntity);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }).orElse(
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    @PutMapping(path = "/users/{id}")
    public ResponseEntity<UserDto> fullUpdateUser(
            @PathVariable Integer id,
            @RequestBody UserDto userDto
    ) {
        if (!userService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        userDto.setUserId(id);
        UserEntity userEntity = userMapper.mapFrom(userDto);

        UserEntity savedUserEntity = userService.save(userEntity);
        return new ResponseEntity<>(
                userMapper.mapTo(savedUserEntity), HttpStatus.OK
        );
    }

    @PatchMapping(path = "/users/{id}")
    public ResponseEntity<UserDto> partialUpdateUser(
            @PathVariable Integer id,
            @RequestBody UserDto userDto
    ) {
        if (!userService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        UserEntity userEntity = userMapper.mapFrom(userDto);
        UserEntity updatedUserEntity = userService.partialUpdate(id, userEntity);
        return new ResponseEntity<>(
                userMapper.mapTo(updatedUserEntity), HttpStatus.OK
        );
    }

    @DeleteMapping(path = "/users/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        if (!userService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
