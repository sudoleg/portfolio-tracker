package com.sudoleg.portfoliomanager.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PortfolioDto {
    @Schema(description = "Automatically generated portfolio ID", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer portfolioId;

    @Schema(description = "Name of the portfolio", example = "My Portfolio", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Schema(description = "User ID of the portfolio owner", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer userId;
}
