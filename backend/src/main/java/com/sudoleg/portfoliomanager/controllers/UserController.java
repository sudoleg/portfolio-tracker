package com.sudoleg.portfoliomanager.controllers;

import com.sudoleg.portfoliomanager.domain.dto.PortfolioDto;
import com.sudoleg.portfoliomanager.domain.dto.UserDto;
import com.sudoleg.portfoliomanager.domain.entities.PortfolioEntity;
import com.sudoleg.portfoliomanager.domain.entities.UserEntity;
import com.sudoleg.portfoliomanager.mappers.Mapper;
import com.sudoleg.portfoliomanager.services.PortfolioService;
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
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final PortfolioService portfolioService;

    private final Mapper<UserEntity, UserDto> userMapper;
    private final Mapper<PortfolioEntity, PortfolioDto> portfolioMapper;

    public UserController(UserService userService, PortfolioService portfolioService, Mapper<UserEntity, UserDto> userMapper, Mapper<PortfolioEntity, PortfolioDto> portfolioMapper) {
        this.userService = userService;
        this.portfolioService = portfolioService;
        this.userMapper = userMapper;
        this.portfolioMapper = portfolioMapper;
    }

    @PostMapping(path = "")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) {
        UserEntity userEntity = userMapper.mapFrom(user);
        UserEntity savedUser = userService.save(userEntity);
        return new ResponseEntity<>(userMapper.mapTo(savedUser), HttpStatus.CREATED);
    }

    @PostMapping(path = "/{id}/portfolios")
    public ResponseEntity<PortfolioDto> createPortfolioForUser(
            @PathVariable Integer id,
            @RequestBody PortfolioDto portfolioDto) {
        Optional<UserEntity> userEntity = userService.findOne(id);
        return userEntity.map(user -> {
            PortfolioEntity portfolioEntity = portfolioMapper.mapFrom(portfolioDto);
            portfolioEntity.setUserEntity(user);
            PortfolioEntity saved = portfolioService.save(portfolioEntity);
            PortfolioDto returnedPortfolioDto = portfolioMapper.mapTo(saved);
            return new ResponseEntity<>(returnedPortfolioDto, HttpStatus.OK);
        }).orElse(
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    @GetMapping(path = "")
    public List<UserDto> listUsers() {
        List<UserEntity> users = userService.findAll();
        return users.stream().map(userMapper::mapTo).collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Integer id) {
        Optional<UserEntity> result = userService.findOne(id);
        return result.map(userEntity -> {
            UserDto userDto = userMapper.mapTo(userEntity);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }).orElse(
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    @GetMapping(path = "", params = "username")
    public ResponseEntity<UserDto> getUserByUsername(@RequestParam String username) {
        Optional<UserEntity> result = userService.getUserByUsername(username);
        return result.map(userEntity -> {
            UserDto userDto = userMapper.mapTo(userEntity);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UserDto> fullUpdateUser(
            @PathVariable Integer id,
            @RequestBody UserDto userDto
    ) {
        if (!userService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        userDto.setId(id);
        UserEntity userEntity = userMapper.mapFrom(userDto);

        UserEntity savedUserEntity = userService.save(userEntity);
        return new ResponseEntity<>(
                userMapper.mapTo(savedUserEntity), HttpStatus.OK
        );
    }

    @PatchMapping(path = "/{id}")
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

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        if (!userService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
