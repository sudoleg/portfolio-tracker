package com.sudoleg.portfoliomanager;

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
}
