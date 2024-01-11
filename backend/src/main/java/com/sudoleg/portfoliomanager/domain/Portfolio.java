package com.sudoleg.portfoliomanager.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Portfolio {

    private Integer portfolioId;
    private String name;
    private Integer userId;

}
