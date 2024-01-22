package com.sudoleg.portfoliomanager;

import com.sudoleg.portfoliomanager.domain.Portfolio;
import com.sudoleg.portfoliomanager.domain.User;

public class TestDataUtil {

    private TestDataUtil() {
    }

    public static User createTestUserA() {
        return User.builder()
                .userId(1)
                .username("johnd")
                .name("John")
                .surname("Doe")
                .email("john.doe@world.com")
                .build();
    }

    public static User createTestUserB() {
        return User.builder()
                .userId(2)
                .username("perterp")
                .name("Peter")
                .surname("Parker")
                .email("peter.parker@marvel.com")
                .build();
    }

    public static User createTestUserC() {
        return User.builder()
                .userId(3)
                .username("maryj")
                .name("Mary")
                .surname("Jane")
                .email("mary.jane@marvel.com")
                .build();
    }

    public static Portfolio createTestPortfolioA(final User user) {
        return Portfolio.builder()
                .portfolioId(1)
                .name("foo")
                .user(user)
                .build();
    }

    public static Portfolio createTestPortfolioB(final User user) {
        return Portfolio.builder()
                .portfolioId(2)
                .name("bar")
                .user(user)
                .build();
    }

    public static Portfolio createTestPortfolioC(final User user) {
        return Portfolio.builder()
                .portfolioId(3)
                .name("baz")
                .user(user)
                .build();
    }

}