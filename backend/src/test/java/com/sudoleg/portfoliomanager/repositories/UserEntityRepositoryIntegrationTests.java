package com.sudoleg.portfoliomanager.repositories;

import com.sudoleg.portfoliomanager.TestDataUtil;
import com.sudoleg.portfoliomanager.domain.entities.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
// ensures that the context is cleaned up after each test
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserEntityRepositoryIntegrationTests {

    private UserRepository underTest;

    @Autowired
    public UserEntityRepositoryIntegrationTests(UserRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testUserCreationAndRecall() {
        UserEntity userEntity = TestDataUtil.createTestUserA();
        underTest.save(userEntity);
        Optional<UserEntity> result = underTest.findById(userEntity.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(userEntity);
    }

    @Test
    public void testMultipleUserCreationAndRecall() {
        UserEntity userEntityA = TestDataUtil.createTestUserA();
        UserEntity userEntityB = TestDataUtil.createTestUserB();
        UserEntity userEntityC = TestDataUtil.createTestUserC();
        underTest.save(userEntityA);
        underTest.save(userEntityB);
        underTest.save(userEntityC);

        Iterable<UserEntity> result = underTest.findAll();
        assertThat(result).hasSize(3).containsExactly(userEntityA, userEntityB, userEntityC);
    }

    @Test
    public void testUserUpdate() {
        UserEntity userEntityA = TestDataUtil.createTestUserA();
        underTest.save(userEntityA);
        userEntityA.setEmail("john.doe@updated.com");
        underTest.save(userEntityA);

        Optional<UserEntity> updatedUserA = underTest.findById(userEntityA.getId());
        assertThat(updatedUserA).isPresent();
        assertThat(updatedUserA.get()).isEqualTo(userEntityA);
        assertThat(updatedUserA.get().getEmail()).isEqualTo("john.doe@updated.com");
    }

    @Test
    public void testUserDeletion() {
        UserEntity userEntityA = TestDataUtil.createTestUserA();
        underTest.save(userEntityA);

        underTest.deleteById(userEntityA.getId());
        Optional<UserEntity> result = underTest.findById(userEntityA.getId());
        assertThat(result).isEmpty();
    }

}
