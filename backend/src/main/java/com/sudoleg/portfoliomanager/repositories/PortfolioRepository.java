package com.sudoleg.portfoliomanager.repositories;

import com.sudoleg.portfoliomanager.domain.entities.PortfolioEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for portfolios.
 * Repositories are used to perform database operations automatically.
 */
@Repository
public interface PortfolioRepository extends CrudRepository<PortfolioEntity, Long> {

    List<PortfolioEntity> findByUserEntity_Id(Long userId);

}
