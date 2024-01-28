package com.sudoleg.portfoliomanager.services;

import com.sudoleg.portfoliomanager.domain.entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserEntity save(UserEntity userEntity);

    List<UserEntity> findAll();

    Optional<UserEntity> findOne(Integer id);

    boolean isExists(Integer id);

    UserEntity partialUpdate(Integer id, UserEntity userEntity);

    void delete(Integer id);

    Optional<UserEntity> getUserByUsername(String username);
}
