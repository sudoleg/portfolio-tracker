package com.sudoleg.portfoliomanager.services.impl;

import com.sudoleg.portfoliomanager.domain.entities.PortfolioEntity;
import com.sudoleg.portfoliomanager.repositories.PortfolioRepository;
import com.sudoleg.portfoliomanager.services.PortfolioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PortfolioServiceImpl implements PortfolioService {

    private PortfolioRepository portfolioRepository;

    public PortfolioServiceImpl(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }

    @Override
    public PortfolioEntity save(PortfolioEntity portfolio) {
        return portfolioRepository.save(portfolio);
    }

    @Override
    public List<PortfolioEntity> findAll() {
        return StreamSupport.stream(portfolioRepository
                                .findAll()
                                .spliterator(),
                        false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PortfolioEntity> findOne(Integer id) {
        return portfolioRepository.findById(id);
    }

    @Override
    public boolean isExists(Integer id) {
        return portfolioRepository.existsById(id);
    }

}
