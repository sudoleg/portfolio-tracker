package com.sudoleg.portfoliomanager.dao.impl;

import com.sudoleg.portfoliomanager.dao.PortfolioDAO;
import com.sudoleg.portfoliomanager.domain.Portfolio;
import org.springframework.jdbc.core.JdbcTemplate;

public class PortfolioDAOImpl implements PortfolioDAO {

    private final JdbcTemplate jdbcTemplate;

    public PortfolioDAOImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Portfolio portfolio) {
        jdbcTemplate.update(
                "INSERT INTO portfolios (portfolio_id, name, owner) VALUES (?, ?, ?)",
                portfolio.getPortfolioId(), portfolio.getName(), portfolio.getUserId()
        );

    }

}
