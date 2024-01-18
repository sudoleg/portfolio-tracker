package com.sudoleg.portfoliomanager.dao.impl;

import com.sudoleg.portfoliomanager.TestDataUtil;
import com.sudoleg.portfoliomanager.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserDAOImplIntegrationTest {

    private UserDAOImpl underTest;

    @Autowired
    public UserDAOImplIntegrationTest(UserDAOImpl underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testUserCreationAndRecall() {
        User user = TestDataUtil.createTestUser();
        underTest.create(user);
        Optional<User> result = underTest.readOne(user.getUserId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(user);
    }
}
