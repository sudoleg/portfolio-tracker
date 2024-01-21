//package com.sudoleg.portfoliomanager.repositories;
//
//import com.sudoleg.portfoliomanager.TestDataUtil;
//import com.sudoleg.portfoliomanager.dao.UserDAO;
//import com.sudoleg.portfoliomanager.domain.Portfolio;
//import com.sudoleg.portfoliomanager.domain.User;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.DirtiesContext;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//public class PortfolioDAOIntegrationTest {
//
//    private PortfolioDAOImpl underTest;
//
//    // the framework determines what implementation of userDAO we need
//    private UserDAO userDAO;
//
//    @Autowired
//    public PortfolioDAOIntegrationTest(PortfolioDAOImpl underTest, UserDAO userDAO) {
//        this.underTest = underTest;
//        this.userDAO = userDAO;
//    }
//
//    @Test
//    public void testPortfolioDAOCreationAndRecall() {
//        User user = TestDataUtil.createTestUserA();
//        userDAO.create(user);
//
//        Portfolio portfolio = TestDataUtil.createTestPortfolioA();
//        underTest.create(portfolio);
//
//        Optional<Portfolio> result = underTest.readOne(portfolio.getPortfolioId());
//        assertThat(result).isPresent();
//        assertThat(result.get()).isEqualTo(portfolio);
//    }
//
//    @Test
//    public void testMultiplePortfolioCreationAndRecall() {
//        User userA = TestDataUtil.createTestUserA();
//        User userB = TestDataUtil.createTestUserB();
//        userDAO.create(userA);
//        userDAO.create(userB);
//
//        Portfolio portfolioA = TestDataUtil.createTestPortfolioA();
//        portfolioA.setUserId(userA.getUserId());
//        Portfolio portfolioB = TestDataUtil.createTestPortfolioB();
//        portfolioB.setUserId(userA.getUserId());
//        Portfolio portfolioC = TestDataUtil.createTestPortfolioC();
//        portfolioC.setUserId(userB.getUserId());
//
//        underTest.create(portfolioA);
//        underTest.create(portfolioB);
//        underTest.create(portfolioC);
//
//        List<Portfolio> result = underTest.readMany();
//        assertThat(result).hasSize(3).containsExactly(portfolioA, portfolioB, portfolioC);
//    }
//
//    @Test
//    public void testPortfolioFullUpdate() {
//        User userA = TestDataUtil.createTestUserA();
//        userDAO.create(userA);
//
//        Portfolio portfolioA = TestDataUtil.createTestPortfolioA();
//        underTest.create(portfolioA);
//        portfolioA.setName("UPDATED");
//        underTest.update(portfolioA.getPortfolioId(), portfolioA);
//
//        Optional<Portfolio> result = underTest.readOne(portfolioA.getPortfolioId());
//        assertThat(result).isPresent();
//        assertThat(result.get()).isEqualTo(portfolioA);
//
//        portfolioA.setPortfolioId(156);
//        underTest.update(1, portfolioA);
//
//        result = underTest.readOne(portfolioA.getPortfolioId());
//        assertThat(result).isPresent();
//        assertThat(result.get().getPortfolioId()).isEqualTo(156);
//        assertThat(result.get().getUserId()).isEqualTo(userA.getUserId());
//        assertThat(result.get().getName()).isEqualTo("UPDATED");
//    }
//
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
//
//}
