package com.sudoleg.portfoliomanager.dao.impl;

import com.sudoleg.portfoliomanager.TestDataUtil;
import com.sudoleg.portfoliomanager.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserDAOImplTests {

    // create an instance of jdbcTemplate automatically - mock
    @Mock
    private JdbcTemplate jdbcTemplate;

    // inject the mocked jdbcTemplate into UserDAO
    @InjectMocks
    private UserDAOImpl underTest;

    @Test
    public void testCreateUserGeneratesCorrectSQL() {
        User user = TestDataUtil.createTestUserA();

        underTest.create(user);

        verify(jdbcTemplate).update(
                eq("INSERT INTO users (user_id, username, name, surname, email) VALUES (?, ?, ?, ?, ?)"),
                eq(1), eq("johnd"), eq("John"), eq("Doe"), eq("john.doe@world.com")
        );

    }

    @Test
    public void testReadOneGeneratesCorrectSQL() {
        underTest.readOne(1);
        verify(jdbcTemplate).query(
                eq("SELECT * FROM users WHERE user_id = ? LIMIT 1"),
                ArgumentMatchers.<UserDAOImpl.UserRowMapper>any(),
                eq(1)
        );
    }

    @Test
    public void testReadManyGeneratesCorrectSQL() {
        underTest.readMany();
        verify(jdbcTemplate).query(
                eq("SELECT * FROM users"),
                ArgumentMatchers.<UserDAOImpl.UserRowMapper>any()
        );
    }

    @Test
    public void testFullUserUpdateGeneratesCorrectSQL() {
        User userA = TestDataUtil.createTestUserA();
        underTest.update(156, userA);
        verify(jdbcTemplate).update(
                "UPDATE users SET user_id = ?, username = ?, name = ?, surname = ?, email = ? WHERE user_id = ?",
                userA.getUserId(), userA.getUsername(), userA.getName(), userA.getSurname(), userA.getEmail(), 156
        );
    }

}
