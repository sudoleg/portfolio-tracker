package com.sudoleg.portfoliomanager.repositories;

import com.sudoleg.portfoliomanager.domain.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// With Spring JPA we don't need to provide an implementation for the interface.
@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
}
