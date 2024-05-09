package com.sudoleg.portfoliomanager.services;

import com.sudoleg.portfoliomanager.domain.dto.PortfolioDto;
import com.sudoleg.portfoliomanager.domain.entities.PortfolioEntity;

import java.util.List;
import java.util.Optional;

public interface PortfolioService {

    PortfolioEntity save(PortfolioEntity portfolioEntity);

    List<PortfolioEntity> findAll();

    Optional<PortfolioEntity> findOne(Integer id);

    boolean isExists(Integer id);

    PortfolioEntity partialUpdate(Integer id, PortfolioEntity portfolioEntity);

    void delete(Integer id);

    PortfolioEntity createPortfolio(PortfolioDto requestDTO);
}
