package com.sudoleg.portfoliomanager;

import com.sudoleg.portfoliomanager.domain.dto.PortfolioDto;
import com.sudoleg.portfoliomanager.domain.dto.UserDto;
import com.sudoleg.portfoliomanager.domain.entities.PortfolioEntity;
import com.sudoleg.portfoliomanager.domain.entities.UserEntity;

public class TestDataUtil {

    private TestDataUtil() {
    }

    public static UserEntity createTestUserA() {
        return UserEntity.builder()
                .id(1)
                .username("johnd")
                .name("John")
                .surname("Doe")
                .email("john.doe@world.com")
                .build();
    }

    public static UserDto createTestUserDtoA() {
        return UserDto.builder()
                .id(1)
                .username("johnd")
                .name("John")
                .surname("Doe")
                .email("john.doe@world.com")
                .build();
    }

    public static UserEntity createTestUserB() {
        return UserEntity.builder()
                .id(2)
                .username("perterp")
                .name("Peter")
                .surname("Parker")
                .email("peter.parker@marvel.com")
                .build();
    }

    public static UserEntity createTestUserC() {
        return UserEntity.builder()
                .id(3)
                .username("maryj")
                .name("Mary")
                .surname("Jane")
                .email("mary.jane@marvel.com")
                .build();
    }

    public static PortfolioEntity createTestPortfolioEntityA(final UserEntity userEntity) {
        return PortfolioEntity.builder()
                .portfolioId(1)
                .name("foo")
                .userEntity(userEntity)
                .build();
    }

    public static PortfolioDto createTestPortfolioDtoA(final UserDto userDto) {
        return PortfolioDto.builder()
                .portfolioId(1)
                .name("foo")
                .userId(userDto.getId())
                .build();
    }

    public static PortfolioEntity createTestPortfolioB(final UserEntity userEntity) {
        return PortfolioEntity.builder()
                .portfolioId(2)
                .name("bar")
                .userEntity(userEntity)
                .build();
    }

    public static PortfolioEntity createTestPortfolioC(final UserEntity userEntity) {
        return PortfolioEntity.builder()
                .portfolioId(3)
                .name("baz")
                .userEntity(userEntity)
                .build();
    }

}