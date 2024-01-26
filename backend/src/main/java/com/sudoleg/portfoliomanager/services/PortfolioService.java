package com.sudoleg.portfoliomanager.services;

import com.sudoleg.portfoliomanager.domain.entities.PortfolioEntity;

import java.util.List;

public interface PortfolioService {

    PortfolioEntity createPortfolio(PortfolioEntity portfolioEntity);

    List<PortfolioEntity> findAll();
}
