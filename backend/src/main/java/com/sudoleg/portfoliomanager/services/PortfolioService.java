package com.sudoleg.portfoliomanager.services;

import com.sudoleg.portfoliomanager.domain.entities.PortfolioEntity;

import java.util.List;
import java.util.Optional;

public interface PortfolioService {

    PortfolioEntity createPortfolio(PortfolioEntity portfolioEntity);

    List<PortfolioEntity> findAll();

    Optional<PortfolioEntity> findOne(Integer id);

}
