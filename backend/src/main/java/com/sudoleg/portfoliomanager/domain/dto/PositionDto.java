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
public class PositionDto {

    @Schema(description = "ID of the position", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Reference to portfolio (by it's ID)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer portfolioId;

    @Schema(description = "Quantity", example = "42", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer quantity;

    @Schema(description = "Reference to financial instrument (by it's ID)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Long financialInstrumentId;

    @Schema(description = "Purchase price", example = "182.17", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Double purchasePrice;

}
