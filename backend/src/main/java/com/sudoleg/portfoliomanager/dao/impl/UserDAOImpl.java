package com.sudoleg.portfoliomanager.dao.impl;

import com.sudoleg.portfoliomanager.dao.UserDAO;
import com.sudoleg.portfoliomanager.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;

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
}
