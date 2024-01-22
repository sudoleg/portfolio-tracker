package com.sudoleg.portfoliomanager.repositories;

import com.sudoleg.portfoliomanager.TestDataUtil;
import com.sudoleg.portfoliomanager.domain.User;
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
public class UserRepositoryIntegrationTests {

    private UserRepository underTest;

    @Autowired
    public UserRepositoryIntegrationTests(UserRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testUserCreationAndRecall() {
        User user = TestDataUtil.createTestUserA();
        underTest.save(user);
        Optional<User> result = underTest.findById(user.getUserId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(user);
    }

    @Test
    public void testMultipleUserCreationAndRecall() {
        User userA = TestDataUtil.createTestUserA();
        User userB = TestDataUtil.createTestUserB();
        User userC = TestDataUtil.createTestUserC();
        underTest.save(userA);
        underTest.save(userB);
        underTest.save(userC);

        Iterable<User> result = underTest.findAll();
        assertThat(result).hasSize(3).containsExactly(userA, userB, userC);
    }

    @Test
    public void testUserUpdate() {
        User userA = TestDataUtil.createTestUserA();
        underTest.save(userA);
        userA.setEmail("john.doe@updated.com");
        underTest.save(userA);

        Optional<User> updatedUserA = underTest.findById(userA.getUserId());
        assertThat(updatedUserA).isPresent();
        assertThat(updatedUserA.get()).isEqualTo(userA);
        assertThat(updatedUserA.get().getEmail()).isEqualTo("john.doe@updated.com");
    }

    @Test
    public void testUserDeletion() {
        User userA = TestDataUtil.createTestUserA();
        underTest.save(userA);

        underTest.deleteById(userA.getUserId());
        Optional<User> result = underTest.findById(userA.getUserId());
        assertThat(result).isEmpty();
    }

}
