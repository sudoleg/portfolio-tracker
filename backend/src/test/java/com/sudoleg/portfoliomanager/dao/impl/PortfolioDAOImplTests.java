package com.sudoleg.portfoliomanager.dao.impl;

import com.sudoleg.portfoliomanager.TestDataUtil;
import com.sudoleg.portfoliomanager.domain.Portfolio;
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
public class PortfolioDAOImplTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private PortfolioDAOImpl underTest;

    @Test
    public void testCreatePortfolioGeneratesCorrectSQL() {

        Portfolio portfolio = TestDataUtil.createTestPortfolioA();

        underTest.create(portfolio);

        verify(jdbcTemplate).update(
                eq("INSERT INTO portfolios (portfolio_id, name, owner) VALUES (?, ?, ?)"),
                eq(1), eq("foo"), eq(1)
        );

    }

    @Test
    public void testReadOneGeneratesCorrectSQL() {
        underTest.readOne(1);
        verify(jdbcTemplate).query(
                eq("SELECT * FROM portfolios WHERE portfolio_id = ? LIMIT 1"),
                ArgumentMatchers.<PortfolioDAOImpl.PortfolioRowMapper>any(),
                eq(1)
        );
    }

    @Test
    public void testReadManyGeneratesCorrectSQL() {
        underTest.readMany();
        verify(jdbcTemplate).query(
                eq("SELECT * FROM portfolios"),
                ArgumentMatchers.<PortfolioDAOImpl.PortfolioRowMapper>any()
        );
    }

    @Test
    public void testFullPortfolioUpdateGeneratesCorrectSQL() {
        Portfolio portfolioA = TestDataUtil.createTestPortfolioA();
        underTest.update(156, portfolioA);
        verify(jdbcTemplate).update(
                "UPDATE portfolios SET portfolio_id = ?, name = ?, owner = ? WHERE portfolio_id = ?",
                portfolioA.getPortfolioId(), portfolioA.getName(), portfolioA.getUserId(), 156
        );
    }

}
