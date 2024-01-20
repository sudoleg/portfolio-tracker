package com.sudoleg.portfoliomanager.dao.impl;

import com.sudoleg.portfoliomanager.dao.PortfolioDAO;
import com.sudoleg.portfoliomanager.domain.Portfolio;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
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

    @Override
    public Optional<Portfolio> readOne(Integer portfolioId) {
        List<Portfolio> results = jdbcTemplate.query(
                "SELECT * FROM portfolios WHERE portfolio_id = ? LIMIT 1",
                new PortfolioRowMapper(), portfolioId
        );
        return results.stream().findFirst();
    }

    @Override
    public List<Portfolio> readMany() {
        return jdbcTemplate.query(
                "SELECT * FROM portfolios",
                new PortfolioRowMapper()
        );
    }

    public static class PortfolioRowMapper implements RowMapper<Portfolio> {
        @Override
        public Portfolio mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Portfolio.builder()
                    .portfolioId(rs.getInt("portfolio_id"))
                    .name(rs.getString("name"))
                    .userId(rs.getInt("owner"))
                    .build();
        }
    }
}
