package com.sudoleg.portfoliomanager.services;

import com.sudoleg.portfoliomanager.domain.entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserEntity save(UserEntity userEntity);

    List<UserEntity> findAll();

    Optional<UserEntity> findOne(Long id);

    Optional<UserEntity> findByIdentifier(String identifier);

    boolean isExists(Long id);

    UserEntity partialUpdate(Long id, UserEntity userEntity);

    void delete(Long id);

    Optional<UserEntity> getUserByUsername(String username);

}
