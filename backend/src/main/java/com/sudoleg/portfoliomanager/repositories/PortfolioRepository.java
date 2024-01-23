package com.sudoleg.portfoliomanager.repositories;

import com.sudoleg.portfoliomanager.domain.entities.PortfolioEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioRepository extends CrudRepository<PortfolioEntity, Integer> {
}
