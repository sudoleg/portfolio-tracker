package com.sudoleg.portfoliomanager.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PortfolioDto {

    private Integer portfolioId;
    private String name;
    private UserDto user;

}
