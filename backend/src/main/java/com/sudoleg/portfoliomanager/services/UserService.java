package com.sudoleg.portfoliomanager.services;

import com.sudoleg.portfoliomanager.domain.entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserEntity createUser(UserEntity userEntity);

    List<UserEntity> findAll();

    Optional<UserEntity> findOne(Integer id);
}
