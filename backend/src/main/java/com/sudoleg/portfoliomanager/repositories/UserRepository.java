package com.sudoleg.portfoliomanager.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sudoleg.portfoliomanager.domain.entities.UserEntity;

/**
 * Repository for users.
 * Repositories are used to perform database operations automatically.
 */
@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByEmail(String email);

}
