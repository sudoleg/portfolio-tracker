package com.sudoleg.portfoliomanager.services;

import java.util.List;
import java.util.Optional;

import com.sudoleg.portfoliomanager.domain.dto.PositionDto;
import com.sudoleg.portfoliomanager.domain.entities.PositionEntity;

public interface PositionService {

    PositionEntity save(PositionDto positionDto);

    Optional<PositionEntity> findOne(Long id);

    List<PositionEntity> findAll();

    List<PositionEntity> findAllByPortfolioId(Integer portfolioId);

    void delete(Long id);

}
