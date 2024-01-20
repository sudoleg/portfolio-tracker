package com.sudoleg.portfoliomanager.dao.impl;

import com.sudoleg.portfoliomanager.TestDataUtil;
import com.sudoleg.portfoliomanager.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
// ensures that the context is cleaned up after each test
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserDAOImplIntegrationTest {

    private UserDAOImpl underTest;

    @Autowired
    public UserDAOImplIntegrationTest(UserDAOImpl underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testUserCreationAndRecall() {
        User user = TestDataUtil.createTestUserA();
        underTest.create(user);
        Optional<User> result = underTest.readOne(user.getUserId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(user);
    }

    @Test
    public void testMultipleUserCreationAndRecall() {
        User userA = TestDataUtil.createTestUserA();
        User userB = TestDataUtil.createTestUserB();
        User userC = TestDataUtil.createTestUserC();
        underTest.create(userA);
        underTest.create(userB);
        underTest.create(userC);

        List<User> result = underTest.readMany();
        assertThat(result).hasSize(3).containsExactly(userA, userB, userC);
    }

    @Test
    public void testUserUpdate() {
        User userA = TestDataUtil.createTestUserA();
        underTest.create(userA);
        userA.setEmail("john.doe@updated.com");
        underTest.update(userA.getUserId(), userA);

        Optional<User> updatedUserA = underTest.readOne(userA.getUserId());
        assertThat(updatedUserA).isPresent();
        assertThat(updatedUserA.get()).isEqualTo(userA);
    }

}
