package com.sudoleg.portfoliomanager.services.impl;

import com.sudoleg.portfoliomanager.domain.dto.PositionDto;
import com.sudoleg.portfoliomanager.domain.entities.PositionEntity;
import com.sudoleg.portfoliomanager.mappers.Mapper;
import com.sudoleg.portfoliomanager.repositories.FinancialInstrumentRepository;
import com.sudoleg.portfoliomanager.repositories.PortfolioRepository;
import com.sudoleg.portfoliomanager.repositories.PositionRepository;
import com.sudoleg.portfoliomanager.services.PositionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PositionServiceImpl implements PositionService {

    private final PositionRepository positionRepository;
    private final PortfolioRepository portfolioRepository;
    private final FinancialInstrumentRepository financialInstrumentRepository;
    private final Mapper<PositionEntity, PositionDto> mapper;

    public PositionServiceImpl(PositionRepository positionRepository, Mapper<PositionEntity, PositionDto> mapper, PortfolioRepository portfolioRepository, FinancialInstrumentRepository financialInstrumentRepository) {
        this.positionRepository = positionRepository;
        this.portfolioRepository = portfolioRepository;
        this.financialInstrumentRepository = financialInstrumentRepository;
        this.mapper = mapper;
    }

    @Override
    public PositionEntity save(PositionDto positionDto) {

        if (!portfolioRepository.existsById(positionDto.getPortfolioId())) {
            throw new EntityNotFoundException("Specified portfolio not found!");
        }

        if (!financialInstrumentRepository.existsById(positionDto.getFinancialInstrumentId())) {
            throw new EntityNotFoundException("Specified security not found!");
        }

        PositionEntity positionEntity = mapper.mapFromDto(positionDto);
        return positionRepository.save(positionEntity);
    }

    @Override
    public Optional<PositionEntity> findOne(Long id) {
        return positionRepository.findById(id);
    }

    @Override
    public List<PositionEntity> findAll() {
        return StreamSupport.stream(positionRepository
                                .findAll()
                                .spliterator(),
                        false)
                .collect(Collectors.toList());
    }

    @Override
    public List<PositionEntity> findAllByPortfolioId(Integer portfolioId) {
        if (!portfolioRepository.existsById(portfolioId)) {
            throw new EntityNotFoundException("Portfolio not found!");
        }
        return positionRepository.findByPortfolioPortfolioId(portfolioId);
    }

}
