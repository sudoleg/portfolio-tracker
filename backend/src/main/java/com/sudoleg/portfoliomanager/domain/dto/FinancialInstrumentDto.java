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
public class FinancialInstrumentDto {

    @Schema(description = "Automatically generated ID for internal use", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "ISIN", example = "US0378331005", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String isin;

    @Schema(description = "Name of the financial instrument", example = "AAPL", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String name;

    @Schema(description = "Description of the financial instrument",
            example = "Apple Inc. is an American multinational corporation and technology company headquartered in Cupertino, California, in Silicon Valley. It designs, develops, and sells consumer electronics, computer software, and online services.",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String description;

}
