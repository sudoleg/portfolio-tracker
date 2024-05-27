package com.sudoleg.portfoliomanager.services;

import com.sudoleg.portfoliomanager.domain.dto.PortfolioDto;
import com.sudoleg.portfoliomanager.domain.entities.PortfolioEntity;

import java.util.List;
import java.util.Optional;

public interface PortfolioService {

    PortfolioEntity save(PortfolioEntity portfolioEntity);

    List<PortfolioEntity> findAll();

    Optional<PortfolioEntity> findOne(Long id);

    boolean isExists(Long id);

    PortfolioEntity partialUpdate(Long id, PortfolioEntity portfolioEntity);

    void delete(Long id);

    PortfolioEntity createPortfolio(PortfolioDto requestDTO);

    List<PortfolioEntity> getUsersPortfolios(Long userId);
}
