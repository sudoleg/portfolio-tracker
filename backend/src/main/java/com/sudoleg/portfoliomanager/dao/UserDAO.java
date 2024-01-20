package com.sudoleg.portfoliomanager.dao;

import com.sudoleg.portfoliomanager.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    void create(User user);

    Optional<User> readOne(Integer i);

    List<User> readMany();
}
