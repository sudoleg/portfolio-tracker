package com.sudoleg.portfoliomanager.repositories;

import com.sudoleg.portfoliomanager.TestDataUtil;
import com.sudoleg.portfoliomanager.domain.entities.PortfolioEntity;
import com.sudoleg.portfoliomanager.domain.entities.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PortfolioEntityRepositoryIntegrationTests {

    private PortfolioRepository underTest;

    @Autowired
    public PortfolioEntityRepositoryIntegrationTests(PortfolioRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testPortfolioDAOCreationAndRecall() {
        UserEntity userEntity = TestDataUtil.createTestUserA();
        PortfolioEntity portfolioEntity = TestDataUtil.createTestPortfolioEntityA(userEntity);
        underTest.save(portfolioEntity);

        Optional<PortfolioEntity> result = underTest.findById(portfolioEntity.getPortfolioId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(portfolioEntity);
    }

    @Test
    public void testMultiplePortfolioCreationAndRecall() {
        UserEntity userEntityA = TestDataUtil.createTestUserA();
        UserEntity userEntityB = TestDataUtil.createTestUserB();

        PortfolioEntity portfolioEntityA = TestDataUtil.createTestPortfolioEntityA(userEntityA);
        PortfolioEntity portfolioEntityB = TestDataUtil.createTestPortfolioB(userEntityA);
        PortfolioEntity portfolioEntityC = TestDataUtil.createTestPortfolioC(userEntityB);

        underTest.save(portfolioEntityA);
        underTest.save(portfolioEntityB);
        underTest.save(portfolioEntityC);

        Iterable<PortfolioEntity> result = underTest.findAll();
        assertThat(result).hasSize(3).containsExactly(portfolioEntityA, portfolioEntityB, portfolioEntityC);

        Optional<PortfolioEntity> retrievedPortfolioA = underTest.findById(portfolioEntityA.getPortfolioId());
        assertThat(retrievedPortfolioA).isPresent();
        assertThat(retrievedPortfolioA.get().getUserEntity()).isEqualTo(userEntityA);
    }

    @Test
    public void testPortfolioFullUpdate() {
        UserEntity userEntityA = TestDataUtil.createTestUserA();
        PortfolioEntity portfolioEntityA = TestDataUtil.createTestPortfolioEntityA(userEntityA);
        underTest.save(portfolioEntityA);
        portfolioEntityA.setName("UPDATED");
        underTest.save(portfolioEntityA);

        Optional<PortfolioEntity> result = underTest.findById(portfolioEntityA.getPortfolioId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(portfolioEntityA);
    }

    @Test
    public void testPortfolioDeletion() {
        UserEntity userEntityA = TestDataUtil.createTestUserA();
        PortfolioEntity portfolioEntityA = TestDataUtil.createTestPortfolioEntityA(userEntityA);
        underTest.save(portfolioEntityA);

        underTest.deleteById(portfolioEntityA.getPortfolioId());
        Optional<PortfolioEntity> result = underTest.findById(portfolioEntityA.getPortfolioId());
        assertThat(result).isEmpty();
    }

}
