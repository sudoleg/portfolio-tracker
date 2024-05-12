package com.sudoleg.portfoliomanager.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a holding or position of a financial instrument in a user's investment portfolio.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "positions")
public class PositionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "position_id_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "portfolio_id", nullable = false)
    private PortfolioEntity portfolio;

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "financial_instrument_id", nullable = false)
    private FinancialInstrumentEntity financialInstrumentEntity;

    @Column(nullable = false)
    private Double purchasePrice;

}
