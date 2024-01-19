package com.sudoleg.portfoliomanager;

import com.sudoleg.portfoliomanager.domain.Portfolio;
import com.sudoleg.portfoliomanager.domain.User;

public final class TestDataUtil {

    private TestDataUtil() {
    }

    public static User createTestUser() {
        return User.builder()
                .userId(1)
                .username("johnd")
                .name("John")
                .surname("Doe")
                .email("john.doe@world.com")
                .build();
    }

    public static Portfolio createTestPortfolio() {
        return Portfolio.builder()
                .portfolioId(1)
                .name("world")
                .userId(1)
                .build();
    }
}
