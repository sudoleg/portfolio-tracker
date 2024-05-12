package com.sudoleg.portfoliomanager.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a financial instrument in the financial markets. This entity includes
 * both a system-generated ID for internal use and the ISIN which is a unique global
 * identifier used for publicly traded securities.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "financial_instruments")
public class FinancialInstrument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 12)
    private String isin;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String description;

}
