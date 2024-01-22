package com.sudoleg.portfoliomanager.repositories;

import com.sudoleg.portfoliomanager.TestDataUtil;
import com.sudoleg.portfoliomanager.domain.Portfolio;
import com.sudoleg.portfoliomanager.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PortfolioRepositoryIntegrationTests {

    private PortfolioRepository underTest;

    @Autowired
    public PortfolioRepositoryIntegrationTests(PortfolioRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testPortfolioDAOCreationAndRecall() {
        User user = TestDataUtil.createTestUserA();
        Portfolio portfolio = TestDataUtil.createTestPortfolioA(user);
        underTest.save(portfolio);

        Optional<Portfolio> result = underTest.findById(portfolio.getPortfolioId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(portfolio);
    }

    @Test
    public void testMultiplePortfolioCreationAndRecall() {
        User userA = TestDataUtil.createTestUserA();
        User userB = TestDataUtil.createTestUserB();

        Portfolio portfolioA = TestDataUtil.createTestPortfolioA(userA);
        Portfolio portfolioB = TestDataUtil.createTestPortfolioB(userA);
        Portfolio portfolioC = TestDataUtil.createTestPortfolioC(userB);

        underTest.save(portfolioA);
        underTest.save(portfolioB);
        underTest.save(portfolioC);

        Iterable<Portfolio> result = underTest.findAll();
        assertThat(result).hasSize(3).containsExactly(portfolioA, portfolioB, portfolioC);

        Optional<Portfolio> retrievedPortfolioA = underTest.findById(portfolioA.getPortfolioId());
        assertThat(retrievedPortfolioA).isPresent();
        assertThat(retrievedPortfolioA.get().getUser()).isEqualTo(userA);
    }

    @Test
    public void testPortfolioFullUpdate() {
        User userA = TestDataUtil.createTestUserA();
        Portfolio portfolioA = TestDataUtil.createTestPortfolioA(userA);
        underTest.save(portfolioA);
        portfolioA.setName("UPDATED");
        underTest.save(portfolioA);

        Optional<Portfolio> result = underTest.findById(portfolioA.getPortfolioId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(portfolioA);
    }

//    @Test
//    public void testPortfolioDeletion() {
//        User userA = TestDataUtil.createTestUserA();
//        userDAO.create(userA);
//
//        Portfolio portfolioA = TestDataUtil.createTestPortfolioA();
//        underTest.create(portfolioA);
//
//        underTest.delete(portfolioA.getPortfolioId());
//        Optional<Portfolio> result = underTest.readOne(portfolioA.getPortfolioId());
//        assertThat(result).isEmpty();
//    }

}
