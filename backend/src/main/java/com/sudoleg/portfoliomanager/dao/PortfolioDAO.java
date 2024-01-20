package com.sudoleg.portfoliomanager.dao;

import com.sudoleg.portfoliomanager.domain.Portfolio;

import java.util.List;
import java.util.Optional;

public interface PortfolioDAO {
    void create(Portfolio portfolio);

    Optional<Portfolio> readOne(Integer i);

    List<Portfolio> readMany();
}
