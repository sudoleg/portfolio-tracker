package com.sudoleg.portfoliomanager.repositories;

import com.sudoleg.portfoliomanager.domain.entities.PositionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionRepository extends CrudRepository<PositionEntity, Long> {
    List<PositionEntity> findByPortfolioId(Long portfolioId);
}
