package com.sudoleg.portfoliomanager.dao;

import com.sudoleg.portfoliomanager.domain.Portfolio;

import java.util.List;
import java.util.Optional;

public interface PortfolioDAO {
    void create(Portfolio portfolio);

    Optional<Portfolio> readOne(Integer portfolioId);

    List<Portfolio> readMany();

    void update(Integer portfolioId, Portfolio portfolio);

    void delete(Integer portfolioId);
}
