package com.sudoleg.portfoliomanager.services.impl;

import com.sudoleg.portfoliomanager.domain.entities.UserEntity;
import com.sudoleg.portfoliomanager.repositories.UserRepository;
import com.sudoleg.portfoliomanager.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
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
    public Optional<UserEntity> findOne(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public boolean isExists(Integer id) {
        return userRepository.existsById(id);
    }

    @Override
    public UserEntity partialUpdate(Integer id, UserEntity userEntity) {
        userEntity.setUserId(id);
        return userRepository.findById(id).map(existingUser -> {
            Optional.ofNullable(userEntity.getUsername()).ifPresent(existingUser::setUsername);
            Optional.ofNullable(userEntity.getName()).ifPresent(existingUser::setName);
            Optional.ofNullable(userEntity.getSurname()).ifPresent(existingUser::setSurname);
            Optional.ofNullable(userEntity.getEmail()).ifPresent(existingUser::setEmail);
            return userRepository.save(existingUser);
        }).orElseThrow(() -> new RuntimeException("User doesn't exist!"));
    }

}
