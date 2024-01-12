package com.sudoleg.portfoliomanager.dao;

import com.sudoleg.portfoliomanager.dao.impl.PortfolioDAOImpl;
import com.sudoleg.portfoliomanager.domain.Portfolio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

        Portfolio portfolio = Portfolio.builder()
                .portfolioId(1)
                .name("world")
                .userId(1)
                .build();

        underTest.create(portfolio);

        verify(jdbcTemplate).update(
                eq("INSERT INTO portfolios (portfolio_id, name, owner) VALUES (?, ?, ?)"),
                eq(1), eq("world"), eq(1)
        );

    }

}
