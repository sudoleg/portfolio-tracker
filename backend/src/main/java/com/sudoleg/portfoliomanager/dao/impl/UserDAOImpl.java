package com.sudoleg.portfoliomanager.dao.impl;

import com.sudoleg.portfoliomanager.dao.UserDAO;
import com.sudoleg.portfoliomanager.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class UserDAOImpl implements UserDAO {

    private final JdbcTemplate jdbcTemplate;

    public UserDAOImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(User user) {
        jdbcTemplate.update(
                "INSERT INTO users (user_id, username, name, surname, email) VALUES (?, ?, ?, ?, ?)",
                user.getUserId(), user.getUsername(), user.getName(), user.getSurname(), user.getEmail()
        );
    }

    @Override
    public Optional<User> readOne(Integer userId) {
        List<User> results = jdbcTemplate.query(
                "SELECT * FROM users WHERE user_id = ? LIMIT 1",
                new UserRowMapper(), userId
        );
        return results.stream().findFirst();
    }

    // converts from result set to an object - in this case User object
    public static class UserRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return User.builder()
                    .userId(rs.getInt("user_id"))
                    .username(rs.getString("username"))
                    .name(rs.getString("name"))
                    .surname(rs.getString("surname"))
                    .email(rs.getString("email"))
                    .build();
        }
    }

}
