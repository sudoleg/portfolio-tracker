package com.sudoleg.portfoliomanager.services.impl;

import com.sudoleg.portfoliomanager.domain.entities.PortfolioEntity;
import com.sudoleg.portfoliomanager.repositories.PortfolioRepository;
import com.sudoleg.portfoliomanager.services.PortfolioService;
import org.springframework.stereotype.Service;

@Service
public class PortfolioServiceImpl implements PortfolioService {

    private PortfolioRepository portfolioRepository;

    public PortfolioServiceImpl(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }

    @Override
    public PortfolioEntity createPortfolio(PortfolioEntity portfolio) {
        return portfolioRepository.save(portfolio);
    }

}
