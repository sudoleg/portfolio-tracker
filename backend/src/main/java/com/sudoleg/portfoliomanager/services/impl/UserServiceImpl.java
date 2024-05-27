package com.sudoleg.portfoliomanager.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.sudoleg.portfoliomanager.domain.entities.UserEntity;
import com.sudoleg.portfoliomanager.exceptions.UserAlreadyExistsException;
import com.sudoleg.portfoliomanager.repositories.UserRepository;
import com.sudoleg.portfoliomanager.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        checkUsernameTaken(userEntity.getUsername());
        checkEmailTaken(userEntity.getEmail());
        return userRepository.save(userEntity);
    }

    private void checkUsernameTaken(String username) {
        boolean exists = userRepository.findByUsername(username).isPresent();
        if (exists)
            throw new UserAlreadyExistsException("Username '" + username + "' is already taken!");
    }

    private void checkEmailTaken(String email) {
        boolean exists = userRepository.findByUsername(email).isPresent();
        if (exists)
            throw new UserAlreadyExistsException("Email '" + email + "' is already taken!");
    }

    @Override
    public List<UserEntity> findAll() {
        return StreamSupport.stream(userRepository
                .findAll()
                .spliterator(),
                false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserEntity> findOne(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Find user by identifier (username or email).
     */
    @Override
    public Optional<UserEntity> findByIdentifier(String identifier) {
        Optional<UserEntity> userEntity = userRepository.findByUsername(identifier);

        if (userEntity.isEmpty()) {
            userEntity = userRepository.findByEmail(identifier);
        }

        return userEntity;
    }

    @Override
    public boolean isExists(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public UserEntity partialUpdate(Long id, UserEntity userEntity) {
        userEntity.setId(id);
        return userRepository.findById(id).map(existingUser -> {
            Optional.ofNullable(userEntity.getUsername()).ifPresent(existingUser::setUsername);
            Optional.ofNullable(userEntity.getName()).ifPresent(existingUser::setName);
            Optional.ofNullable(userEntity.getSurname()).ifPresent(existingUser::setSurname);
            Optional.ofNullable(userEntity.getEmail()).ifPresent(existingUser::setEmail);
            return userRepository.save(existingUser);
        }).orElseThrow(() -> new RuntimeException("User doesn't exist!"));
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<UserEntity> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
