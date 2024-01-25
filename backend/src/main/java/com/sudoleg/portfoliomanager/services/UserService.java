package com.sudoleg.portfoliomanager.services;

import com.sudoleg.portfoliomanager.domain.entities.UserEntity;

import java.util.List;

public interface UserService {

    UserEntity createUser(UserEntity userEntity);

    List<UserEntity> findAll();
}
