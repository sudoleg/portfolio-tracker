package com.sudoleg.portfoliomanager.dao.impl;

import com.sudoleg.portfoliomanager.TestDataUtil;
import com.sudoleg.portfoliomanager.dao.UserDAO;
import com.sudoleg.portfoliomanager.domain.Portfolio;
import com.sudoleg.portfoliomanager.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PortfolioDAOIntegrationTest {

    private PortfolioDAOImpl underTest;

    // the framework determines what implementation of userDAO we need
    private UserDAO userDAO;

    @Autowired
    public PortfolioDAOIntegrationTest(PortfolioDAOImpl underTest, UserDAO userDAO) {
        this.underTest = underTest;
        this.userDAO = userDAO;
    }

    @Test
    public void testPortfolioDAOCreationAndRecall() {
        User user = TestDataUtil.createTestUserA();
        userDAO.create(user);

        Portfolio portfolio = TestDataUtil.createTestPortfolio();
        underTest.create(portfolio);

        Optional<Portfolio> result = underTest.readOne(portfolio.getPortfolioId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(portfolio);
    }

}
