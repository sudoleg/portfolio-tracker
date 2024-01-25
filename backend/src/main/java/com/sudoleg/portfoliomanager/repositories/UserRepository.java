package com.sudoleg.portfoliomanager.repositories;

import com.sudoleg.portfoliomanager.domain.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for users.
 * Repositories are used to perform database operations automatically.
 */
@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
}
