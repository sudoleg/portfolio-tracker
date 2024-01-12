package com.sudoleg.portfoliomanager.dao;

import com.sudoleg.portfoliomanager.dao.impl.UserDAOImpl;
import com.sudoleg.portfoliomanager.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
        User user = User.builder()
                        .userId(1)
                        .username("johnd")
                        .name("John")
                        .surname("Doe")
                        .email("john.doe@world.com")
                        .build();

        underTest.create(user);

        verify(jdbcTemplate).update(
                eq("INSERT INTO users (user_id, username, name, surname, email) VALUES (?, ?, ?, ?, ?)"),
                eq(1), eq("johnd"), eq("John"), eq("Doe"), eq("john.doe@world.com")
        );

    }


}
